package com.gmail.etozhesergius.android_course;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.content.Context;

public class ContactListFragment extends ListFragment {
    static final Contact[] contacts = {
            new Contact("Roman Romanov", "+8-800-55-35-35"),
            new Contact("Тот самый", "112"),
            new Contact("Мышка", "65465766786"),
            new Contact("Крыска", "11212"),
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Список контактов");
        ArrayAdapter<Contact> contactAdapter = new ArrayAdapter<Contact>(getActivity(), 0, contacts) {
            @NonNull
            @Override
            public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.fragment_contact_list, null, false);
                }
                TextView nameView = convertView.findViewById(R.id.tv_name);
                TextView phoneNumberView = convertView.findViewById(R.id.tv_phoneNumber);
                Contact currentContact = contacts[i];
                nameView.setText(currentContact.getName());
                phoneNumberView.setText(currentContact.getPhoneNumber());

                return convertView;
            }
        };
        setListAdapter(contactAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView listView, @Nullable View view, int i, long id) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_list, ContactDetailsFragment.newInstance(i));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}