package com.ou.components;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

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

    public void addUser(Map<String, Object> dataMap) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(); // Automatically generates a new document ID
        String docId = docRef.getId();

        dataMap.put("createAt", Timestamp.now());
        dataMap.put("id", docId);

        docRef.set(dataMap);
    }

    public void addUserToFirstRoom(String userId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference rooms = db.collection("rooms");

        ApiFuture<QuerySnapshot> querySnapshot = rooms.limit(1).get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if (!documents.isEmpty()) {
            DocumentReference firstRoom = documents.get(0).getReference();
            firstRoom.update("members", FieldValue.arrayUnion(userId));
        }
    }

    public void updateDocument(String collectionName, String documentId, Map<String, Object> updatedData) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);

        // Update data in Firestore document
        ApiFuture<WriteResult> result = docRef.update(updatedData);
        result.get(); // Optional: Wait for the result if needed
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

        dataMap.put("createAt", Timestamp.now());
        dataMap.put("id", docId);

        ApiFuture<WriteResult> result = docRef.set(dataMap);
    }
}
