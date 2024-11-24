![Screenshot_20241124_010924](https://github.com/user-attachments/assets/4496675c-cb17-45ec-a205-a58ad2f31986)
![Screenshot_20241124_011000](https://github.com/user-attachments/assets/88ff7024-6f14-4b19-b1fc-bc1bf140fb86)
![Screenshot_20241124_011022](https://github.com/user-attachments/assets/88be084f-093a-431b-a766-b5231d4d4a79)

### How to run the app

## a smaple Apk is availible to download

## Alternative:

### Sync the Project
### Open your project in Android Studio.
### Go to File > Sync Project with Gradle Files to ensure all dependencies are downloaded and integrated.
## Connect a physical Android device via USB and enable Developer Options and USB Debugging.
### Alternatively, start an Android emulator from Device Manager in Android Studio.'


## What i would have added:
### Implement pagination for loading large data


# Architecture Pattern:
## 1. The reason for Multi-Module Architecture Pattern to  have a scalable code base and to Achieve Separation of Concerns:



# These are Seperated As Follows:
## OnBoarding Layer
## Common Layer
## Home Layer
## Network Layer
## Data Layer
## Auth Layer
## app Layer

## App Screenshot

# Libraries Used On this Project:

## 1. Android Jetpack Library

## 2. Hilt For Dependency Injection

## 3. Retrofit for (Network Request)

## 4. Coil For (Image Loading)

## 5. Room Database For (Offline Caching)

## 6. Preference DataStore for (passing Data)

## 7. Kotlin Coroutine For (Async Task)

## 8. RecyclerView For (ListItems)


# Architectural Pattern Used:
## Mvvm Architecture Pattern

# Test:

## Local Test:
## 1. Test ViewModel And LiveData gets the Value When It comes from the network

## Integration Test:
## 2. Database Test when Inserting Data into the Database Is Observed And Not Empty

## End To End Test
## 3. Espresso Test
## 4. Manual Testing





