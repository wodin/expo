package expo.modules.updates.selectionpolicy;

import android.util.Log;

import org.json.JSONObject;

import java.util.Iterator;

import expo.modules.updates.db.entity.UpdateEntity;

public class SelectionPolicies {

  public static final String TAG = SelectionPolicies.class.getSimpleName();

  public static boolean matchesFilters(UpdateEntity update, JSONObject manifestFilters) {
    if (manifestFilters == null || update.metadata == null || !update.metadata.has("updateMetadata")) {
      // empty matches all
      return true;
    }
    try {
      JSONObject updateMetadata = update.metadata.getJSONObject("updateMetadata");

      // create lowercase copy for case-insensitive search
      JSONObject metadataLCKeys = new JSONObject();
      Iterator<String> metadataKeySet = updateMetadata.keys();
      while (metadataKeySet.hasNext()) {
        String key = metadataKeySet.next();
        metadataLCKeys.put(key.toLowerCase(), updateMetadata.get(key));
      }

      Iterator<String> filterKeySet = manifestFilters.keys();
      while (filterKeySet.hasNext()) {
        String key = filterKeySet.next();
        // once an update fails one filter, break early; we don't need to check the rest
        if (metadataLCKeys.has(key) && !manifestFilters.get(key).equals(metadataLCKeys.get(key))) {
          return false;
        }
      }
    } catch (Exception e) {
      Log.e(TAG, "Error filtering manifest using server data", e);
      return true;
    }
    // as long as the update doesn't violate a filter, it passes
    return true;
  }
}
