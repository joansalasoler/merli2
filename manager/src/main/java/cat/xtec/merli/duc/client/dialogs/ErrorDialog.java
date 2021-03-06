package cat.xtec.merli.duc.client.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;


/**
 *
 */
public class ErrorDialog {

    /** Primary CSS style for this widget */
    public static final String STYLE_NAME = "duc-Dialog";

    /** UiBinder instance */
    private static final Binder binder = GWT.create(Binder.class);

    /** UiBinder interface */
    @UiTemplate("ErrorDialog.ui.xml")
    interface Binder extends UiBinder<DialogBox, ErrorDialog> {}

    /** Dialog box */
    private DialogBox dialog;

    /** Confirm button */
    @UiField Button confirm;


    /**
     * Instantiates a new dialog.
     */
    protected ErrorDialog() {
        dialog = binder.createAndBindUi(this);
        dialog.addStyleName(STYLE_NAME);
        dialog.setGlassEnabled(true);
        RootPanel.get().add(dialog);
    }


    /**
     * Shows a new confirmation dialog.
     */
    public static void show() {
        new ErrorDialog().show(confirm -> {});
    }


    /**
     * Shows a new confirmation dialog.
     *
     * @param callback      Dialog callback
     */
    public static void confirm(ConfirmCallback callback) {
        new ErrorDialog().show(callback);
    }


    /**
     * Shows the dialog and attaches the button handles to
     * the provided callback.
     *
     * @param callback      Dialog callback
     */
    protected void show(ConfirmCallback callback) {
        confirm.addClickHandler(e -> {
            dialog.hide();
            callback.onResponse(true);
        });

        dialog.center();
    }

}
