package com.gmail.etozhesergius.android_course;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

public class ContactDetailsFragment extends Fragment {
    private ContactsService sService;
    private TextView detailedContactName;
    private TextView detailedContactNumber;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ServiceInterface) {
            this.sService = ((ServiceInterface) context).getService();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detailedContactName = null;
        detailedContactNumber = null;
    }

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
        detailedContactName = (TextView) view.findViewById(R.id.detailedContactName);
        detailedContactNumber = (TextView) view.findViewById(R.id.detailedContactNumber);
        sService.getContact(callback, index);
        return view;
    }

    public interface ResultListener {
        void onComplete(Contact result);
    }

    private ContactDetailsFragment.ResultListener callback = new ContactDetailsFragment.ResultListener() {
        @Override
        public void onComplete(Contact result) {
            final Contact contact = result;
            detailedContactName.post(new Runnable() {
                @Override
                public void run() {
                    detailedContactName.setText(contact.getName());
                    detailedContactNumber.setText(contact.getPhoneNumber());
                }
            });

        }
    };
}

