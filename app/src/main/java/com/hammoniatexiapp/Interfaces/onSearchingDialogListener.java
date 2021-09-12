package com.hammoniatexiapp.Interfaces;


import com.hammoniatexiapp.pojos.ModelCurrentBooking;

public interface onSearchingDialogListener {
    void onRequestAccepted(ModelCurrentBooking data);
    void onRequestCancel();
    void onDriverNotFound();
}
