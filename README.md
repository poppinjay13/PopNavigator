[![](https://jitpack.io/v/poppinjay13/PopNavigator.svg)](https://jitpack.io/#poppinjay13/PopNavigator)[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# PopNavigator
An android navigation bar with animated transitions on menu items.
The library is built to support [Adobe After Effects](http://www.adobe.com/products/aftereffects.html) animations exported as json using [Lottie](https://github.com/airbnb/lottie-android) to renders them natively on mobile while providing navigation functionality.

## Screenshots

![Demo GIF](https://media.giphy.com/media/gGqoXqSVyacidb5nUi/giphy.gif)


## Installation

To add PopNavigator to your project:

1.Add Jitpack to your project build.gradle file.

```java
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2.Add the dependency to your app `build.gradle` file.

```java
dependencies {
 implementation 'com.github.poppinjay13:PopNavigator:$popNavigatorVersion'
}
```
Note that replacing `$popNavigatorVersion` with the code for the latest commit would work as well


3.Syncing the project's gradle should allow you to use the library in your project.

## Usage

A straight forward implementation would be to add it to your activity's XML as

```xml
    <com.poppinjay13.popnavigator.PopNavigator
        android:id="@+id/popNavigator"
        android:layout_width="match_parent"
        android:layout_height="75dp"
        android:layout_alignParentBottom="true"/>
```

In your activity:

```java
PopNavigator popNavigator = findViewById(R.id.poppinjay13_navbar);
popNavigator.addMenuItem(PopNavigator.BASE_EXPLORE);
popNavigator.addMenuItem(PopNavigator.BASE_NOTIFY);
popNavigator.addMenuItem(PopNavigator.BASE_USER);
```

The package comes predefined with a few animations out of the box hence `PopNavigator.$name` provides the animation specified. 

Menu items are loaded in the order in which you add them to the menu.

A sample apk file will be uploaded soon to allow for previews as well as updates to functionality.

## Customization
      
You can customize most of the properties of the popNavigator as it extends a LinearLayout and all LinearLayout methods can be used.

Custom animations can be added simply by uploading the json file into your res/raw folder then referencing it using

```java
  popNavigator.addItem(R.raw.my_animation);
```

## Support

The PopNavigator library is maintained and improved on nights and weekends and if it helps you out please consider supporting the developers by sponsoring a coffee or milkshake.

Feel free to fork the repo and contribute, star and follow to stay updated on releases.

## Releases

Each release provides an apk to allow for installation and testing of the library on a basic basis. 

The latest release can be found [here](https://github.com/poppinjay13/PopNavigator/releases/).
## License

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
         
      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   
Copyright 2020 [Ian Odundo](https:://poppinjay13.github.io)
