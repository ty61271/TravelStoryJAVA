package com.expamle.love.travelstory.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.expamle.love.travelstory.R;
import com.expamle.love.travelstory.SuggestionActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class UserFragment extends Fragment {

    private static final int RC_SIGN_IN = 100;
    private Button googleLogin;
    private Button suggestion;
    private FirebaseAuth auth;
    private Button facebookLogin;
    private Button signOut;
    private FirebaseUser user;
    private GoogleAuth googleAuth=new GoogleAuth();
    private FacebookAuth facebookAuth=new FacebookAuth();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        findViews();
        if (user == null) {
            googleLogin.setVisibility(View.VISIBLE);
            facebookLogin.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.GONE);
        } else {
            googleLogin.setVisibility(View.GONE);
            facebookLogin.setVisibility(View.GONE);
            signOut.setVisibility(View.VISIBLE);
        }


    }

    private void findViews() {
        googleLogin = getActivity().findViewById(R.id.bt_googleLogin);
        facebookLogin = getActivity().findViewById(R.id.bt_fbLogin);
        signOut = getActivity().findViewById(R.id.sign_out);
        suggestion = getActivity().findViewById(R.id.bt_suggestion);
        googleOnClick();
        facebookOnClick();
        signOutOnClick();
        suggestOnClick();
    }

    private void facebookOnClick() {

        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.addAuthStateListener(facebookAuth);
            }
        });
    }

    private void signOutOnClick() {
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(getActivity());
                googleLogin.setVisibility(View.VISIBLE);
                facebookLogin.setVisibility(View.VISIBLE);
                signOut.setVisibility(View.GONE);
                auth.removeAuthStateListener(googleAuth);
                auth.removeAuthStateListener(facebookAuth);
            }
        });
    }

    private void googleOnClick() {

        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.addAuthStateListener(googleAuth);
            }
        });
    }

    private void suggestOnClick() {
//        找意見回饋的按鈕轉到意見回饋頁面

        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SuggestionActivity.class);
                startActivity(intent);
            }
        });
    }


    class FacebookAuth implements FirebaseAuth.AuthStateListener {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            user=firebaseAuth.getCurrentUser();
            if (user == null) {
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.FacebookBuilder().build()
                                ))
                                .setIsSmartLockEnabled(false)
                                .build(),
                        RC_SIGN_IN);
            } else {

            }
            if (user == null) {
                googleLogin.setVisibility(View.VISIBLE);
                facebookLogin.setVisibility(View.VISIBLE);
                signOut.setVisibility(View.GONE);
            } else {
                googleLogin.setVisibility(View.GONE);
                facebookLogin.setVisibility(View.GONE);
                signOut.setVisibility(View.VISIBLE);
            }
        }
    }

    class GoogleAuth implements FirebaseAuth.AuthStateListener {

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            user=firebaseAuth.getCurrentUser();
            if (user == null) {
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.GoogleBuilder().build()
                                ))
                                .setIsSmartLockEnabled(false)
                                .build(),
                        RC_SIGN_IN);
            } else {

            }
            if (user == null) {
                googleLogin.setVisibility(View.VISIBLE);
                facebookLogin.setVisibility(View.VISIBLE);
                signOut.setVisibility(View.GONE);
            } else {
                googleLogin.setVisibility(View.GONE);
                facebookLogin.setVisibility(View.GONE);
                signOut.setVisibility(View.VISIBLE);
            }

        }

    }

}

