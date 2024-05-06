# STO 증권형 토큰 거래 앱 및 사이트

Front 개발일지

### 24.05.02

1. 개발환경 설정
```bash
npx create-expo-app front
npx expo install react-native-screens react-native-safe-area-context
npm install expo-linear-gradient
npm install expo-font
npm install expo-app-loading
npm install @react-navigation/material-bottom-tabs @react-navigation/stack axios expo-constants
npm install redux react-redux @reduxjs/toolkit
npm install @react-navigation/native
```
2. 폴더구조 생성
- assets
    - images
    - fonts
- src
    - screens
    - components
    - constants
    - routers
    - stores
    - utils

### 24.05.03

1. 알림 기능 구현
- FCM
```bash
npx expo install @react-native-firebase/app
npx expo install @react-native-firebase/messaging
```

### 오류 및 수정

1. login 시 아이디 하이픈 들어감 ( 헤결 )
    - SignInScreen.js
    - phoneNum: phoneNumber
    => phoneNum: phoneNumber.replace(/-/g,'')

2. login 아이디 입력 시
    - 01012345678 치고 실수로 하나 더 입력하면 하나 더 들어가있음
    - 01012345678a 하면 로그인 오류발생