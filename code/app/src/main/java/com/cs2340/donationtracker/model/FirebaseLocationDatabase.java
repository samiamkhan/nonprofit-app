package com.cs2340.donationtracker.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements Firebase operations to retrieve Location information.
 */
public class FirebaseLocationDatabase {

    //Up to date data snapshot of locations
    private DataSnapshot locationsData;

    /**
     * Creates a new FirebaseLocationDatabase.
     */
    public FirebaseLocationDatabase() {
        //Instance of Firebase
        DatabaseReference locationDatabase = FirebaseDatabase.getInstance().getReference()
                .child("locations");
        locationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locationsData = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    /**
     * Gets all location names from the database.
     *
     * @return a list of all location names
     */
    public List<String> getLocationNames() {
        List<String> locations = new ArrayList<>();

        for (DataSnapshot childSnapshot : locationsData.getChildren()) {
            locations.add(childSnapshot.child("name").getValue().toString());
        }

        return locations;
    }

    /**
     * Gets a Location based on its ID.
     *
     * @param locationID location's ID to lookup
     * @return Location object of matching location
     */
    public Location getLocation(int locationID) {
        return locationsData.child(Integer.toString(locationID)).getValue(Location.class);
    }
}
