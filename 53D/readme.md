# Outline

## Week 1 - 08/09/2025
### Subject: Course introduction and work environment setup

#### Topic breakdown:
* Syllabus, teaching methodology and bibliography.
  * Evaluation
  * Resources
  
### Practical Class
* Goal: Install Android Studio and create a new project (the *Hello Android* app) using Andrdoid Studio's wizard.

#### For reference:
* [Download Android Studio & App Tools](https://developer.android.com/studio)
* [Android API Levels](https://apilevels.com/)
* [SDK Platform release notes | Android Developers](https://developer.android.com/studio/releases/platforms)
* [Coding Conventions | Kotlin Documentation](https://kotlinlang.org/docs/coding-conventions.html)
* [Compose layout basics](https://developer.android.com/develop/ui/compose/layouts/basics)

## Week 2 - 15/09/2025
### Subject: Building a UI with Jetpack Compose - Introduction

#### Topic breakdown:
* The Activity component as the UI host
  * Basic lifecycle: onCreate, onStart, onStop and onDestroy
  * Lifecycle: Behavior on navigation between activities
* Jetpack Compose (revisions from TDS course)
  * @Composable functions: the building blocks of the UI
  * Mental model: the UI as a function of state (state -> @Composable -> UI)
  * Stateless @Composable functions
    * Elementary UI elements: Text, Button, Image
    * Layouts: Column, Row, Box
* UX: navigation between activities
  * Intents: explicit and implicit
  * User task and back stack

#### For reference:
* [Thinking in Compose | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/mental-model)
* [Material components in Compose](https://developer.android.com/develop/ui/compose/components)
* [Compose Layout Basics](https://developer.android.com/develop/ui/compose/layouts/basics)
* [Preview your UI with composable previews | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/tooling/previews)
* [Tasks and the back stack | Android Developers](https://developer.android.com/guide/components/activities/tasks-and-back-stack)
* [Intents and intent filters | Android Developers](https://developer.android.com/guide/components/intents-filters)
  * [Sending the user to another app | Android Developers](https://developer.android.com/guide/components/intents-filters)
  * [Common intents | Android Developers](https://developer.android.com/guide/components/intents-common)
  


## Week 3 - 29/09/2025

#### Topic breakdown:
* Jetpack Compose
  * Stateless vs. stateful @Composables, `remember` and `mutableStateOf` 
  * State hoisting: lifting the state to the parent composable
* Architecting the UI: introduction


#### For reference:
* [State and Jetpack Compose](https://developer.android.com/develop/ui/compose/state#state-and-composition)
* [Architecting your Compose UI](https://developer.android.com/develop/ui/compose/architecture)
* [Guide to app architecture](https://developer.android.com/topic/architecture)

## Week 4 - 6/10/2025

#### Topic breakdown:
* Architecting the UI: continuation
  * Immutability
  * View States
* Automatic testing in Android
  * Unit tests with JUnit
  * Instrumented UI tests
  * Testing @Composable functions with Compose Testing

#### For reference:
* [State and Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/state#managing-state) 
* [Architecting your Compose UI | Jetpack Compose | Android Developers](https://developer.android.com/jetpack/compose/architecture)
* [Guide to application architecture](https://developer.android.com/jetpack/guide)
* [Test your Compose layout](https://developer.android.com/develop/ui/compose/testing) 
* [Test apps on Android](https://developer.android.com/training/testing)


## Week 5 - 13/10/2025
### Subject: Building a UI with Jetpack Compose - State management (introduction to MVVM)

* The Activity component: continued
  * Lifecycle revisited: behavior on configuration changes (e.g. screen rotation)
  * Implications of configuration changes for state management
* Jetpack Compose: continued
  * Preservation of presentation state
    * `rememberSaveable`
    * `Parcelable` contract and `Parcelize` annotation
	* `Saver`
* Architectural considerations
  * Designing the UI as a state machine
  * Introduction to MVVM (Model-View-ViewModel) in Android
  * ViewModel component
    * Lifecycle: behavior on configuration changes
    * ViewModel and presentation state: host for the UI state machine
  

#### For reference:
* [Save UI state in Compose | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/state-saving)
* [State and Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/state#restore-ui-state)
* [Parcelable implementation generator | Kotlin | Android Developers](https://developer.android.com/kotlin/parcelize) 
* [ViewModel Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
  * [View Model lifecycle](https://developer.android.com/topic/libraries/architecture/viewmodel#lifecycle)
  * [Use Kotlin Coroutines with lifecycle-aware components](https://developer.android.com/topic/libraries/architecture/coroutines#viewmodelscope)
* [ViewModelStore](https://developer.android.com/reference/androidx/lifecycle/ViewModelStore)
* [ViewModelStoreOwner](https://developer.android.com/reference/androidx/lifecycle/ViewModelStoreOwner)

## Week 6 - 20/10/2025

### Subject: Beyond the UI - Manual Dependency Injection

* Application resources: internationalization and localization
* ViewModel, revisited
  * Instantiating using a custom factory, to enable parametric construction
  * Automated testing of the ViewModel
* Application
  * Motivation and lifecycle
  * Usage for dependency resolution (as a Service Locator)
  
### For reference:
* [Resources in Compose](https://developer.android.com/jetpack/compose/resources)
* [Application resources](https://developer.android.com/guide/topics/resources/providing-resources)
* [Application](https://developer.android.com/reference/android/app/Application)
* [Manual dependency injection](https://developer.android.com/training/dependency-injection/manual)
* [Testing Coroutines on Android](https://developer.android.com/kotlin/coroutines/test)
* [Saved State module for ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate)

## Week 7 - 27/10/2025

### Subject: Beyond the UI - ViewModel (continued)

### Subject: Android concurrency model
* Overview of concurrency in Android
  * Main thread vs background threads
  * Constraints on the work performed on the main thread
  * ANR (Application Not Responding) errors
* Kotlin's concurrency model (revision from Concurrent Programming course)
  * Thread vs coroutine
  * Suspending functions, coroutines, scopes e dispatchers
* ViewModel (continued)
  * ViewModelScope
  * Launching coroutines from the ViewModel

## Week 8 - 3/11/2025

### Subject: Local storage with Preferences DataStore

#### Topic breakdown:
* General characterization
  * Data model (i.e. key-value store)
  * Supported data types
* Kotlin concurrency model
  * Flows, revisited 

### For reference:
  * [Asynchronous Flow | Kotlin Documentation](https://kotlinlang.org/docs/flow.html) 
  * [Kotlin flows on Android](https://developer.android.com/kotlin/flow#callback)
  * [Kotlin Coroutines  by Marcin Moskala, chapter 3](https://kt.academy/book/coroutines)
  * [DataStore Overview](https://developer.android.com/topic/libraries/architecture/datastore)
  * [TDD Is The Best Design Technique](https://www.youtube.com/watch?v=ln4WnxX-wrw)
  
  
## Week 10 - 10/11/2025

### Subject: Persistent storage with Firestore 

#### Topic breakdown:
* General characterization
  * Data model (i.e. Document DB)
  * Documents, collections and references
  * Supported data types
* Description of the Android SDK API
  * Adding, updating and deleting data
  * Reading data: queries and observable queries

  
### For reference:
* Prepare your environment for Firebase
  * [Add Firebase to your Android project](https://firebase.google.com/docs/android/setup)
  * [Install, configure and integrate Local Emulator Suite](https://firebase.google.com/docs/emulator-suite/install_and_configure)
  * [Connect your app and start prototyping | Firebase Local Emulator Suite](https://firebase.google.com/docs/emulator-suite/connect_and_prototype?database=Firestore)
* Firestore API
  * [Getting started with Cloud Firestore](https://firebase.google.com/docs/firestore/quickstart)
  * [Cloud Firestore Data model | Firebase](https://firebase.google.com/docs/firestore/data-model) 
  * [Supported data types | Firestore | Firebase](https://firebase.google.com/docs/firestore/manage-data/data-types)
  * [Add data to Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/manage-data/add-data)
  * [Delete data from Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/manage-data/delete-data)
  * [Get data with Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/query-data/get-data)
  * [Get real time updates with Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/query-data/listen)
  * [Firestore API Docs](https://firebase.google.com/docs/reference/kotlin/com/google/firebase/firestore/package-summary)


## Week 11 - 17/11/2025

### Subject: Comunication with HTTP APIs and Persistent storage with Room

#### Topic breakdown:
* The Android application as a HTTP client
  * Motivation and consequences of distribution
  * Required permissions
* HTTP communication with Ktor client
  * Programming model
  * Making assinchrounous requests
* JSON serialization with Kotlinx Serialization
* Discussion, should we use Databases on mobile applications?
* Room characterization
  * Base components and systems
  * Integration workflow
  * One to many relations

### For reference:
* [Connecting to the network](https://developer.android.com/training/basics/network-ops/connecting)
* [Ktor Client](https://ktor.io/docs/client-create-multiplatform-application.html#ktor-dependencies)
  * [Ktor engines](https://ktor.io/docs/client-engines.html)
  * [Content negotiation](https://ktor.io/docs/client-serialization.html)
  * [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)
* [Room](https://developer.android.com/training/data-storage/room#)
* [Database](https://developer.android.com/training/data-storage/room#database)
* [Entities](https://developer.android.com/training/data-storage/room/defining-data)
* [DAOs](https://developer.android.com/training/data-storage/room/accessing-data)
* [Define relationships between objects](https://developer.android.com/training/data-storage/room/relationships)