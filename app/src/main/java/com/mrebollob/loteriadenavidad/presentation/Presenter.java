package com.mrebollob.loteriadenavidad.presentation;

/**
 * @author mrebollob
 */
public class Presenter<T extends Presenter.View> {

    private T view;

    public void setView(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    public void initialize() {

    }

    public void update() {

    }

    public void finalize() {

    }

    public interface View {

        void showLoading();

        void hideLoading();

    }
}