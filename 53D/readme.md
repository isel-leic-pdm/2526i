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
