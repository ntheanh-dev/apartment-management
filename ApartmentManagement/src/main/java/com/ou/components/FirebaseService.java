package com.ou.components;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.ou.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public List<QueryDocumentSnapshot> getUsersByField(String fieldName, String value)
            throws InterruptedException, ExecutionException
    {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection("users");

        Query query = users.whereEqualTo(fieldName, value);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        // Return a list of DocumentSnapshot objects
        return querySnapshot.get().getDocuments();
    }

    public void addUser(User user) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(); // Automatically generates a new document ID
        ApiFuture<com.google.cloud.firestore.WriteResult> result = docRef.set(user);
    }

    public boolean checkCollectionExist(String collectionName) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collection = db.collection(collectionName);
        ApiFuture<QuerySnapshot> querySnapshot = collection.limit(1).get();
        return !querySnapshot.get().isEmpty();
    }

    public void initCollection(String collectionName,Map<String, Object> dataMap) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document();
        String docId = docRef.getId();

        if(!dataMap.containsKey("id")) {
            dataMap.put("id", docId);
        }

        ApiFuture<WriteResult> result = docRef.set(dataMap);
    }
}
