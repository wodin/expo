import React from 'react';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { createStackNavigator } from 'react-navigation-stack';
import { createAppContainer } from 'react-navigation';
import SelectScreen from './screens/SelectScreen';
import TestScreen from './screens/TestScreen';

const MainNavigator = createStackNavigator(
  {
    Select: SelectScreen,
    RunTests: TestScreen,
  },
  { headerMode: 'screen' }
);

const App = createAppContainer(MainNavigator);

export default () => (
  <SafeAreaProvider>
    <App />
  </SafeAreaProvider>
);
