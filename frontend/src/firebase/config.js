// Import the functions you need from the SDKs you need
import { initializeApp } from 'firebase/app';
import 'firebase/firestore';
import { getFirestore } from 'firebase/firestore';
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: 'AIzaSyCT3kqJOS-6BP68r_QuJ_klHBRGiKxVbxo',
    authDomain: 'apartment-managememt.firebaseapp.com',
    projectId: 'apartment-managememt',
    storageBucket: 'apartment-managememt.appspot.com',
    messagingSenderId: '428935411499',
    appId: '1:428935411499:web:485ef7177517b8887c776e',
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

const db = getFirestore(app);

export { db };
