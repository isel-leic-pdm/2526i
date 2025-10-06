# Course Section Outline and Resources

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

#### Extra links:
* [On the Biology of a Large Language Model, Josh Batson of Anthropic @ Stanford](https://www.youtube.com/watch?v=vRQs7qfIDaU)
* [Is Chain-of-Thought Reasoning of LLMs a Mirage? A Data Distribution Lens, Zhao C. et al](https://arxiv.org/pdf/2508.01191v3)
* [On the Biology of a Large Language Model](https://transformer-circuits.pub/2025/attribution-graphs/biology.html#dives-cot)

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

#### Lecture video:
* [Lecture 02 - Building a Compose UI](https://www.youtube.com/live/9pjAzan0kSQ?si=JhYsDhI2_X3mlCe9)

#### For reference:
* [Thinking in Compose | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/mental-model)
* [Material components in Compose](https://developer.android.com/develop/ui/compose/components)
* [Compose Layout Basics](https://developer.android.com/develop/ui/compose/layouts/basics)
* [Preview your UI with composable previews | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/tooling/previews)
* [Tasks and the back stack | Android Developers](https://developer.android.com/guide/components/activities/tasks-and-back-stack)
* [Intents and intent filters | Android Developers](https://developer.android.com/guide/components/intents-filters)
  * [Sending the user to another app | Android Developers](https://developer.android.com/guide/components/intents-filters)
  * [Common intents | Android Developers](https://developer.android.com/guide/components/intents-common)



## Week 3 - 22/09/2025
### Subject: Building a UI with Jetpack Compose - Presentation State

#### Topic breakdown:
* Jetpack Compose:
  * Stateful @Composable functions
    * State and recomposition
    * State management: `remember` and `mutableStateOf`
  * State hoisting
    * Lifting state up
    * Event callbacks
* Automatic testing in Android
  * Unit tests with JUnit
  * Instrumented UI tests with Espresso
  * Testing @Composable functions with Compose Testing

#### Lecture video:
* [Lecture 03 - Building a Compose UI](https://www.youtube.com/live/9pjAzan0kSQ?si=JhYsDhI2_X3mlCe9)

#### For reference:
* [State and Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/state#managing-state) 
* [Architecting your Compose UI | Jetpack Compose | Android Developers](https://developer.android.com/jetpack/compose/architecture)
* [Guide to application architecture](https://developer.android.com/jetpack/guide)
* [Test your Compose layout](https://developer.android.com/develop/ui/compose/testing) 
* [Test apps on Android](https://developer.android.com/training/testing)

## Week 4 - 29/09/2025
### Subject: Building a UI with Jetpack Compose - State management (introduction to MVVM)
* The Activity component: continued
  * Lifecycle revisited: behavior on configuration changes (e.g. screen rotation)
  * Implications of configuration changes for state management
* Jetpack Compose: continued
  * Preservation of presentation state
    * `rememberSaveable`
    * `Parcelable` contract and `Parcelize` annotation
* Architectural considerations
  * Designing the UI as a state machine
  * Introduction to MVVM (Model-View-ViewModel) in Android
  * ViewModel component
    * Lifecycle: behavior on configuration changes
    * ViewModel and presentation state: host for the UI state machine
  
  #### Lecture video:
* [Lecture 04 - Building a Compose UI](https://www.youtube.com/watch?v=DxIqkU9JbbY&list=PL8XxoCaL3dBhQsQz35tCkcasNLAp5NnhA&index=3)

#### For reference:
* [Save UI state in Compose | Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/state-saving)
* [State and Jetpack Compose | Android Developers](https://developer.android.com/develop/ui/compose/state#restore-ui-state)
* [Parcelable implementation generator | Kotlin | Android Developers](https://developer.android.com/kotlin/parcelize) 

## Week 5 - 06/10/2025
### Subject: Beyond the UI - ViewModel
### Subject: Beyond the UI - Manual dependency injection
* ViewModel
  * Purpose and aplicability
  * Lifecycle and relation with the Activity
  * Instantiation and retrieval
* Application class:
  * Purpose and lifecycle
  * Usage for dependency resolution (as a Service Locator)

#### Lecture video:
* [Lecture 05 - Beyond the UI - ViewModel](https://www.youtube.com/watch?v=hEupJHTXNZY&list=PL8XxoCaL3dBhQsQz35tCkcasNLAp5NnhA&index=4)

#### For reference:
* [ViewModel overview | Android Developers](https://developer.android.com/topic/libraries/architecture)
* [ViewModel lifecycle | Android Developers](https://developer.android.com/topic/libraries/architecture/viewmodel#lifecycle)
* [Application class | Android Developers](https://developer.android.com/reference/android/app/Application)
* [Manual dependency injection | Android Developers](https://developer.android.com/training/dependency-injection/manual)

