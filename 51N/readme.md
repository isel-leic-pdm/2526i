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

### Practical Class
* Goal: Install Android Studio and create a new project (the *Hello Android* app) using Andrdoid Studio's wizard.
* Challenge: Complete the first milestone of the class assignment 


#### For reference:
* [Tasks and Back Stack](https://developer.android.com/guide/components/activities/tasks-and-back-stack)
* [Intents and intent filters](https://developer.android.com/guide/components/intents-filters)
  * [Sending the user to another app](https://developer.android.com/training/basics/intents/sending)
  * [Common Intents](https://developer.android.com/guide/components/intents-common)
* [State and Jetpack Compose](https://developer.android.com/develop/ui/compose/state#state-and-composition)
* [Architecting your Compose UI](https://developer.android.com/develop/ui/compose/architecture)
* [Guide to app architecture](https://developer.android.com/topic/architecture)

https://developer.android.com/develop/ui/compose/state#state-and-composition  
  

  * Introduction to the catalog of @Composables
    * Elementar @Composables: `Text`, `Button`
    * Layout @Composables: `Column`, `Row`, `Box`
  * State management in @Composable functions
    * `remember` - for memoizing values
    * `mutableStateOf` - for observable mutable state