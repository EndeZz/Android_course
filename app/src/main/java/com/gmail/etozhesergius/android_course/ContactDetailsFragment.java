package com.gmail.etozhesergius.android_course;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

public class ContactDetailsFragment extends Fragment {

    static ContactDetailsFragment newInstance(int index) {
        ContactDetailsFragment f = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Контакт");
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);
        int index = this.getArguments().getInt("index");
        TextView detailedContactName = (TextView) view.findViewById(R.id.detailedContactName);
        TextView detailedContactNumber = (TextView) view.findViewById(R.id.detailedContactNumber);
        detailedContactName.setText(ContactListFragment.contacts[index].getName());
        detailedContactNumber.setText(ContactListFragment.contacts[index].getPhoneNumber());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setTitle("Список контактов");

    }
}

