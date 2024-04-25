# Pet Care App

# Milestone 1 - Pet Care App (Unit 7)

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview

### Description

Pet care app is a comprehensive and intuitive pet management app designed to simplify the lives of pet owners and enhance the well-being of their pets.

### App Evaluation

App Idea 1: Pet Care App (first priority)
- **Category:** Home Care 
- **Mobile:** This app focuses on providing a seamless experience to taking care of pets. It has many features including updating real-time data to family/group meembers and using user roles and locaiton to send personalized notifications that help with pet care.
- **Story:** This app mainly targets the familys or groups that have pets. This app is very essential and an important addition to any family's needs. This helps save time and effort for each member responsible for the pet and also helps to provide the necessities for the pet promptly. 
- **Market:** As mentioned, this is directly targeted toward pet owners. Since this is a general category, the app can be used by people anywhere in the world, which makes the audience size very large. 
- **Habit:** This app is meant to be used daily by the users, to complete and check tasks that contribute to taking care of the pet. It is not addictive in nature, but the idea of the app itself prompts a cyclic behavior as the people using the app will use it multiple times a day. 
- **Scope:** This app is very clearly thought out, so the approaach to making a fundamental design of this app is direct. Since this app will be personally used by some of our group members, we are excited to work on the fundamentals, even if the fully poolished version will all the features will be hard to dliver before the deadline.

App Idea 2: Trivia 
- **Category:** Games
- **Mobile:** This app uses the GPT API to convert a trivia request by the user to find a database to pull general questions from about a certain topic. This app will use location of the user to have suggestions about what quizzes to generate. Ex. A quiz about the prominance of a certain location when they visit it. It also updates the score of a user in real time as they take the quiz. We can also add leaderboard among the user's friends in the app and update their position on it in real-time. 
- **Story:** This app targets the people looking for a fun passtime or someone who is looking to expand their general knowledge. This is an exciting idea as users get quizzes on-demand without having to browse the internet. Having leaderboard rankings among your peers is also envokes competitive spirit, which is always fun.
- **Market:** Since this app provides a way for users to specify their own category, this will reach a wide audience unlike  pre-specified quizzes online. There is no well-defined audience for this app. 
- **Habit:** The addictive nature of this app depends on the user's want to top the leaderboard against their peers and also gain knowledge along the way. To make the users visit the app habitually, we can add custom notications based on location and time to suggest different categories of quizzes to take, and earn daily points.
- **Scope:** Since this is an abstract and open idea, it is complex to navigate through. Since we are limited by time, the stripped-down version of this app will still be exciting to learn as it teaches us more about using databases and espcially integreating them with GPT API.

App Idea 3: PC Building App 
- **Category:**  Tech
- **Mobile:** The PC Building app helps users build their PC by categorizing and listing parts from an expansive database. The unique feature of this app would be updating each of the parts with the current price that the part is selling for. If the user needs further help, it would allow them to take the quiz to determine their preferneces so that the app can suggest the best parts for the user to buy for their PC. 
- **Story:** The target audience of this app are people that want a desktop PC but do not know where to start in the wide world of building PCs. There are many people (even in our friend groups) who do no have much idea about PC parts and would like to build one in the future, this app would help them greatly. 
- **Market:** Beacuse this app helps people that are looking to upgrade or build their PCs, it has a specific set of audience that it would serve. However, this app is also not very niche as it helps beginners as well as experts as it both suggests and displays crucial data about what parts are avialable and their respective prices. 
- **Habit:** Other than general tech enthusiasts or professionals, this app only is helpful to people who are upgrading or looking to build their PC. There is no addictive proposition for this app. 
- **Scope:** This app involves heavy data manipulation, and would take a lot of time. Other than extra features (like the quiz), the fundamentals of this app would be fun to work on and we would rank this the same as the trivia intems of our inclination to work on the project.

The main motive behind choosing App Idea 1 (pet care app) is because we saw us personally using this app. As we went on through our unique ideas for this app, we also realized that this would help a lot of families and groups as there is nothing in the market competing with our app directly. The uniqueness and the usefulness of this app is what made us ultimately choose it over the other two ideas. 

  
## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

1. Multi-screen app with at least 2 different screens'
2. Pulls data from an API
3. List of items on screen relevant to app
4. Items have sub-values when applicable (e.g., Name, Price, Location)
5. Items have at least 3 responsive functionalities (e.g., Adding items, resetting or deleting items, marking items with a status or value)
6. Android Navigation UI like Bottom Navigation, Drawer Layout, Top Bar, etc.
7. User inputs from previous entries persist
8. Scrollable lists


**Optional Features**

1. Assign Pet Tasks to Group Members
2. Push Notifications
3. Data Persistence with Firebase
4. Authentication with Firebase
5. Onboarding
6. User Customizability
7. Reminders
8. Todo List
9. Groups

### 2. Screen Archetypes

- Home Screen
  - The user will be brought to the Home Screen once they are authenticated
  - From the home screen, a user can quickly add a task or manage current ones 
- Profile Screen
  - The user profile image and name will be displayed here
  - The user will be able to access the app settings from this page
  - The user can view, add and manage groups
  - The user can accept or decline group invitations
- Pets Screen
  - A list of a users pets will be displayed here where the user can manage or add pets 

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Profile
* Home
* Pets

**Flow Navigation** (Screen to Screen)

- Home Screen

- Profile Screen
  - Settings
  - Group Details
- Pets Screen
  - Add pet

## Wireframes

<img src="https://github.com/MNANDO/pet-care-app/assets/17109419/09393ad0-0548-4414-a75d-ca10c915dcb9" width=600>

<br>

<br>

### [BONUS] Digital Wireframes & Mockups
<img width="305" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/fb2fffe0-511d-48a2-aa29-95e1b7f59028">
<img width="306" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/37cff35a-9aae-49db-804e-8b52a304d90e">
<img width="302" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/2d3f51b5-ba66-4edf-9c9d-861c016f50ce">
<img width="302" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/afb6d339-b72e-4a1a-88f0-70efb231d3b1">
<img width="307" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/8ad3aa81-1a42-45d5-bd3b-6dedc6cdfc3f">
<img width="303" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/f5b6c969-ba84-47b5-b4f8-2e8b6c0d5283">
<img width="306" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/ff8a6ec5-36cb-4b76-8c07-d130c07d7179">
<img width="304" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/5933a647-29e5-4ca4-9cf6-1174304e9f60">
<img width="303" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/787877d3-9d5b-4acf-aaed-21493aacbd8b">
<img width="302" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/dcdfdd32-f10e-4b09-a869-608dfb055bb4">
<img width="302" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/53f96a8d-fddc-4a06-bf7a-515cdb62f0f1">
<img width="303" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/2f9c4924-2cbd-4ce1-a106-1b41bc6aaca3">

### High Fidelity Wireframe
<img width="201" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/2a20bc90-e2e6-4bf5-8ef7-61e315ccf740">
<img width="201" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/24f88c34-6096-4522-9881-9b628c5de024">
<img width="202" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/b4cd179f-11dd-4483-862c-b4a4004ee16a">
<img width="201" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/b1184278-4273-43aa-a062-c86f1fda2167">
<img width="199" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/21398f96-7a3f-4c80-8d21-789def2a0747">
<img width="201" alt="image" src="https://github.com/MNANDO/pet-care-app/assets/17109419/3ae7cf2f-ab8a-4f4b-bf12-a23783fd9f4e">

### [BONUS] Interactive Prototype
https://xd.adobe.com/view/c906ab4c-6afa-4d72-8d6e-0c3ce35b531b-9c55/


https://github.com/MNANDO/pet-care-app/assets/17109419/1ed74f68-067d-4bfc-becf-f613b72f70e2


<br>

# Milestone 2 - Build Sprint 1 (Unit 8)

## GitHub Project board

[Add screenshot of your Project Board with three milestones visible in
this section]
<img src="https://github.com/MNANDO/pet-care-app/assets/17109419/fca44abc-d802-42de-9707-0ca6c50412c0" width=600>


## Issue cards

- <img src="https://github.com/MNANDO/pet-care-app/assets/17109419/e8edbc69-9429-499c-bbd6-5459bdcec675" width=600>
- <img src="https://github.com/MNANDO/pet-care-app/assets/17109419/88a4c752-9fe3-4fb2-9742-db462b432303" width=600>

## Issues worked on this sprint

- List the issues you completed this sprint
  - Navigation
<img src="https://github.com/Android-Pet-Care-App/pet-care-app/assets/17109419/01a022c0-0e5a-466a-99d2-e5e4001b4f4d"  width=600>
<br>

# Milestone 3 - Build Sprint 2 (Unit 9)

## GitHub Project board

<img width="1081" alt="image" src="https://github.com/Android-Pet-Care-App/pet-care-app/assets/17109419/387d764b-856c-4018-9e47-16626492d959">

## Completed user stories

- Pet Page State and Logic
- Navigatin State and Logic
  
## List any pending user stories / any user stories you decided to cut
from the original requirements
- Home Page State and Logic
- Firebase

[Add video/gif of your current application that shows build progress]
<img src="https://github.com/Android-Pet-Care-App/pet-care-app/assets/17109419/48cfc73b-b11d-4f69-bef5-a95edd896e2c" width=600>






## App Demo Video

[https://github.com/Android-Pet-Care-App/pet-care-app/assets/17109419/4e225064-b778-49f1-a2ae-77331df0d6c1](https://youtu.be/mfDiQoSxuVc)
