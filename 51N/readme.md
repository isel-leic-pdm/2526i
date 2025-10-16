# Outline

## Week 1 - 08/09/2025
### Subject: Course introduction and work environment setup

#### Topic breakdown:
* Syllabus, teaching methodology and bibliography.
  * Evaluation
  * Resources


#### For reference:
* [Download Android Studio & App Tools](https://developer.android.com/studio)
* [Android API Levels](https://apilevels.com/)
* [SDK Platform release notes | Android Developers](https://developer.android.com/studio/releases/platforms)
* [Coding Conventions | Kotlin Documentation](https://kotlinlang.org/docs/coding-conventions.html)



#### Topic breakdown:
* The Activity component as the UI host
  * Basic lifecycle: `onCreate`, `onStart`, `onStop` and `onDestroy`
  * Concurrency model: execution warranties and constraints of lifecycle methods
* Jetpack Compose 
  * `@Composable` functions: the building blocks of the UI

#### For reference:

* [Thinking in Compose | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/mental-model)
* [Material components in Compose](https://developer.android.com/develop/ui/compose/components)
* [Compose Layout Basics](https://developer.android.com/develop/ui/compose/layouts/basics)
* [Preview your UI with composable previews | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/tooling/previews)


## Week 2 - 15/09/2025


#### Topic breakdown:
* UX: navigating between activities
  * User task and back stack
  * Intents: explicit and implicit
* Jetpack Compose
  * Stateless vs. stateful @Composables, `remember` and `mutableStateOf` 
  * State hoisting: lifting the state to the parent composable
* Architecting the UI: introduction

#### For reference:
* [Tasks and Back Stack](https://developer.android.com/guide/components/activities/tasks-and-back-stack)
* [Intents and intent filters](https://developer.android.com/guide/components/intents-filters)
  * [Sending the user to another app](https://developer.android.com/training/basics/intents/sending)
  * [Common Intents](https://developer.android.com/guide/components/intents-common)
* [State and Jetpack Compose](https://developer.android.com/develop/ui/compose/state#state-and-composition)
* [Architecting your Compose UI](https://developer.android.com/develop/ui/compose/architecture)
* [Guide to app architecture](https://developer.android.com/topic/architecture)

  
## Week 3 - 29/09/2025

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


## Week 4 - 6/10/2025
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


## Week 4 - 6/10/2025
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
* [ViewModelStoreOwner](https://developer.android.com/reference/androidx/lifecycle/ViewModelStoreOwner

## Week 5 - 13/10/2025

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