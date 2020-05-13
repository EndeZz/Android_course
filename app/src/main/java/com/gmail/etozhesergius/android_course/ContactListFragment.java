package com.gmail.etozhesergius.android_course;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.content.Context;

public class ContactListFragment extends ListFragment {
    private ContactsService sService;
    private View view;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ServiceInterface) {
            this.sService = ((ServiceInterface) context).getService();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Список контактов");
        view = getView();
        sService.getContacts(callback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }

    @Override
    public void onListItemClick(@NonNull ListView listView, @Nullable View view, int i, long id) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_list, ContactDetailsFragment.newInstance((int) id));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public interface ResultListener {
        void onComplete(Contact[] result);
    }

    private ResultListener callback = new ResultListener() {
        @Override
        public void onComplete(Contact[] result) {
            final Contact[] contacts = result;
            if (view != null){
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayAdapter<Contact> contactAdapter = new ArrayAdapter<Contact>(getActivity(), 0, contacts){
                            @NonNull
                            @Override
                            public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
                                if (convertView == null) {
                                    convertView = getLayoutInflater().inflate(R.layout.fragment_contact_list, null, false);
                                }
                                ImageView imageView = convertView.findViewById(R.id.contactImage);
                                TextView nameView = convertView.findViewById(R.id.tv_name);
                                TextView phoneNumberView = convertView.findViewById(R.id.tv_phoneNumber);
                                Contact currentContact = contacts[i];
                                nameView.setText(currentContact.getName());
                                phoneNumberView.setText(currentContact.getPhoneNumber());
                                imageView.setImageResource(currentContact.getImage());
                                return convertView;
                            }
                        };
                        setListAdapter(contactAdapter);
                    }
                });
            }
        }
    };
}