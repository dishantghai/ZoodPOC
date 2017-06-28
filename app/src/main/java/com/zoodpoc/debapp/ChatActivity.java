package com.zoodpoc.debapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pblead26 on 28-Jun-17.
 */

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = ChatActivity.class.getName();

    private TextView resultTextView;
    private EditText queryEditText;

    private List<ChatMessage> msgList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChatAdapter mAdapter;

    ChatMessage chatMessage;


    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String msgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        queryEditText = (EditText) findViewById(R.id.textQuery);

        findViewById(R.id.buttonSend).setOnClickListener(this);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node

        mFirebaseDatabase = mFirebaseInstance.getReference("msg_table_debate");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ChatAdapter(msgList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                recyclerView.scrollToPosition(mAdapter.getItemCount()-1);

            }
        });


        mFirebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                if(user.payload!=null) {
                    String message = user.getPayload();
                    chatMessage = new ChatMessage(false, message);
                    msgList.add(chatMessage);
                    mAdapter.notifyDataSetChanged();


                }
                // String userName = map.get("user").toString();

              /*  if(userName.equals(UserDetails.username)){
                    addMessageBox("You:-\n" + message, 1);
                }
                else{
                    addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
                }*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



    }


    private void clearEditText(EditText et){
        et.setText("");
    }

    /*
    * AIRequest should have query OR event
    */
    private void sendRequest() {

      /*  String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();*/


        final String queryString = String.valueOf(queryEditText.getText());

            createUser(queryString, "Pooja");

        chatMessage = new ChatMessage(true, queryString);
        msgList.add(chatMessage);
        mAdapter.notifyDataSetChanged();
        clearEditText(queryEditText);


        if (TextUtils.isEmpty(queryString)) {
            Log.d(TAG, "empty string");
            return;
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSend:
                sendRequest();
                break;

        }
    }


    /**
     * Creating new user node under 'users'
     */
    private void createUser(String payload, String sender_name) {

        Log.e(TAG," CREATE USER CALLED");
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
//        if (TextUtils.isEmpty(userId)) {
            DatabaseReference mUserRef = mFirebaseDatabase.push();

            msgId = mUserRef.getKey();
//        }
        Boolean is_for = true;
        Boolean is_against = false;

        Boolean is_media = false;
        String media_type = "text";
        String media_url = " ";
        String msg_id = "123";
        String send_time = "34234";
        String sender_uid = "002";

        User user = new User(is_for, is_media, media_type, media_url, msg_id, payload, sender_name, send_time, sender_uid);
      //  User user = new User(payload, sender_name);

        mUserRef.setValue(user);

        //String key = mFirebaseDatabase.push().getKey();*/

        addUserListener(msgId);
    }

    /**
     * User data change listener
     */
    private void addUserListener(String userid) {
        // User data change listener
        mFirebaseDatabase.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e(TAG, "ON DATA CHANGE CALLED ");
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.sender_name);

              /*  // Display newly updated name and email
                txtDetails.setText(user.sender_name);

                // clear edit text
                inputEmail.setText("");
                inputName.setText("");*/

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }


}
