WiFi Settings Functional Tests
==============

## Description
This project is compact test framework with functional tests for WiFi Settings apk.

**apk** under test is allocated in the root of framework and named as **app-release.apk**.

**Repository** with apk source code - https://github.com/VYesikov/wifi-settings

## Main task
The story is the following: open wifi settings, add a new wifi
network, ensure it’s added to networks list (device can connect to
this network now).


## Prerequisites
   Required software:
   
   **Graddle** - https://gradle.org/install
   
   **Appium, Node.js, UiAutomator2 Driver** - http://appium.io/docs/en/about-appium/getting-started/?lang=en   

   **AndroidStudio** - https://developer.android.com/studio/index.html
   
   **Android SDK API 27** - download using Android Studio
  
## Configuration

#### Environment Variables
|**Variable name**| **Value / Description**|
|-----------------|----------------|
|platform| **android** / **ios**|
|deviceName| name of **Emulator** or **Real device**|
   
   
## How to run tests

1. Start Appium server 
> [NOTE] This step may be skipped - server will be started automatically from tests
 
2. Start Android Emulator minimum with 27 api level
    
### Run tests via JUnit configuration

1. Create JUnit configuration with environment variables `platform=android`, `deviceName={name of android emulator}`.
2. Run configuration

### Run tests from command line

1. Open command line (Git Bash)
2. Go to the root of the Test Framework
3. Edit run_tests.sh script and set **deviceName** variable to the name of android emulator
3. Run script `sh run_tests.sh`

#### Basic test scenarios

* Add new network and connect to it   
* Add network with same name twice
