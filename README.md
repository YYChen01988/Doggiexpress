# Countries
Display a list of breeds, user can select any breed to see 10 random pictures of the dogs with a top picture and breed name with it.

## 1. Core
This can be accessed by all modules. Inludes utils and abstractions.

## 2. app
With main application and injections and access to all modules. 

## 3. dogBreed
Provides grid view of a list of breeds and images of the selected breed.

# Gradle

## 1. config-android.gradle
    - Contains android configuration. Enabled viewbinding and buildvariants. Flavors needs to be added here and this will be accessed by all libraries build.gradles

## 2. config-properties.gradle
    - Contains properties that needs to be secured. BASE_URL in this code.

## 3. dependencies-all.gradle
    - Dependencies.


# Libraries
1. Koin(DI)
2. Android Architecture (AndroidX,ViewModel,navigation, Lifecycle, LiveData, Coroutines)
3. Glide 4.12.0
4. Retrofit2, OkHttp, Gson
5. Mockito
6. Junit
7. Ktlint
