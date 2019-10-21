import { ImagePickerResult, MediaTypeOptions, ImagePickerOptions, ExportPresets } from './ImagePicker.types';
export declare function launchImageLibraryAsync(options?: ImagePickerOptions): Promise<ImagePickerResult>;
export declare function launchCameraAsync(options?: ImagePickerOptions): Promise<ImagePickerResult>;
export { MediaTypeOptions, ImagePickerOptions, ImagePickerResult, ExportPresets };
