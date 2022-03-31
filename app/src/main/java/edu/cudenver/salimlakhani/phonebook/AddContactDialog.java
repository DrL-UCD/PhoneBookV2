package edu.cudenver.salimlakhani.phonebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import edu.cudenver.salimlakhani.phonebook.databinding.DialogAddContactBinding;

public class AddContactDialog extends DialogFragment {

    private DialogAddContactBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogAddContactBinding.inflate (LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
        binding.addContact.inflateMenu(R.menu.add_menu);

        binding.addContact.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_exit:
                    dismiss();
                    break;
                case R.id.action_save:

            }
            return true;
        });

        binding.buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textInputName.setText ("");
                binding.textInputPhone.setText ("");
                binding.textInputEmail.setText("");
                binding.textInputStreet.setText("");
                binding.textInputCity.setText("");
                binding.textInputState.setText ("");
                binding.textInputZip.setText("");

                binding.radioButtonBusiness.setChecked(true);

                binding.textInputName.requestFocus();
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.textInputName.getText().toString();
                String phone = binding.textInputPhone.getText().toString();
                String email = binding.textInputEmail.getText().toString();
                String street = binding.textInputStreet.getText().toString();
                String city = binding.textInputCity.getText().toString();
                String state = binding.textInputState.getText().toString();
                String zip = binding.textInputZip.getText().toString();

                String contactType = "";

                if (binding.radioButtonBusiness.isChecked()) {
                    contactType = "Business";
                }
                else if (binding.radioButtonFamily.isChecked()) {
                    contactType = "Family";
                }
                else {
                    contactType = "Friend";
                }

                Contact contact = new Contact (name, phone, email, street, city, state, zip,
                        contactType);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.addContact(contact);
                Log.i ("info", "End of Add Contact");
                dismiss();
            }
        });

        return builder.create();
    }
}
