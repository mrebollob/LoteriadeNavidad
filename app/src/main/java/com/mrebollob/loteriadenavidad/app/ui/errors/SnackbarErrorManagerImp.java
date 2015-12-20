package com.mrebollob.loteriadenavidad.app.ui.errors;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author mrebollob
 */
public class SnackbarErrorManagerImp implements ErrorManager {

    private View view;

    public SnackbarErrorManagerImp(@NonNull View view) {
        this.view = view;
    }

    @Override
    public void showError(@NonNull String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
                .show();
    }
}