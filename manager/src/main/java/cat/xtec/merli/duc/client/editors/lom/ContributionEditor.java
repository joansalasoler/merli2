package cat.xtec.merli.duc.client.editors.lom;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.editor.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

import cat.xtec.merli.domain.type.Contribution;
import cat.xtec.merli.duc.client.editors.ContactEditor;
import cat.xtec.merli.duc.client.editors.voc.ContributorRoleEditor;


/**
 * Contribution to the state of a learning object.
 */
public class ContributionEditor extends Composite implements Editor<Contribution> {

    /** Primary CSS style for this widget */
    public static final String STYLE_NAME = "duc-ContributionEditor";

    /** UiBinder instance */
    private static final Binder binder = GWT.create(Binder.class);

    /** UiBinder interface */
    @UiTemplate("ContributionEditor.ui.xml")
    interface Binder extends UiBinder<Widget, ContributionEditor> {}

    /** Type of contribution */
    @UiField ContributorRoleEditor role;

    /** Date of the contribution */
    @UiField DateBox date;

    /** List of contributors */
    @UiField ContactEditor entity;


    /**
     *
     */
    public ContributionEditor() {
        Widget widget = binder.createAndBindUi(this);

        initWidget(widget);
        setStylePrimaryName(STYLE_NAME);
    }

}
