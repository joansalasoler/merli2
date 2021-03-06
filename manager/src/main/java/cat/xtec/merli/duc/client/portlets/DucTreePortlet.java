package cat.xtec.merli.duc.client.portlets;

import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasTreeItems;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import edu.stanford.bmir.protege.web.shared.DataFactory;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.selection.SelectionModel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLEntity;

import cat.xtec.merli.duc.client.LocaleUtils;
import cat.xtec.merli.domain.taxa.Entity;
import cat.xtec.merli.duc.client.services.EntityService;
import cat.xtec.merli.duc.client.services.EntityServiceAsync;
import cat.xtec.merli.duc.client.widgets.EntityTree;
import cat.xtec.merli.duc.client.widgets.EntityTreeItem;
import static cat.xtec.merli.domain.taxa.EntityFlag.*;
import static cat.xtec.merli.duc.client.portlets.DucPortletState.*;


/**
 * This is the base class for the entities hierarchy portlets.
 */
public abstract class DucTreePortlet extends DucPortlet {

    /** Unexpanded internal node placeholder */
    protected static final Label PLACEHOLDER = new Label();

    /** Tree widget for this portlet */
    protected EntityTree tree = new EntityTree();

    /** Container panel for the tree */
    protected ScrollPanel container = new ScrollPanel(tree.asWidget());

    /** RPC service for this portlet */
    protected EntityServiceAsync service = GWT.create(EntityService.class);


    /**
     * {@inheritDoc}
     */
    public DucTreePortlet(SelectionModel selection, ProjectId project) {
        super(selection, project);
        attachTreeHandlers();
        add(container);
    }


    /**
     * Fetches the children of an OWL entity from the server and sets
     * them as the roots of this portlet's tree.
     *
     * @param iri       Root entity IRI identifier
     */
    public void populateRoots(IRI iri) {
        String project = getProjectId();
        setViewState(STATE_WORKING);
        service.children(project, iri, callback);
    }


    /**
     * Expands a collapsed tree item. This method fetches the childs of
     * the item asynchronously from the server and replaces its current
     * children with the response.
     *
     * @param item      Tree item instance
     */
    public void expandTreeItem(EntityTreeItem item) {
        IRI iri = item.getIRI();
        String project = getProjectId();

        service.children(project, iri, new AsyncCallback<List<Entity>>() {
            @Override public void onFailure(Throwable caught) {}
            @Override public void onSuccess(List<Entity> nodes) {
                LocaleUtils.sortEntites(nodes);
                setVertices(item, nodes);
            }
        });
    }


    /**
     * Collapses a previously expanded tree item. This method removes from
     * the DOM all the child items of the node.
     *
     * @param item      Tree item instance
     */
    public void collapseTreeItem(EntityTreeItem item) {
        if (item.getChildCount() > 0) {
            item.removeItems();
            item.addItem(PLACEHOLDER);
        }
    }


    /**
     * Broadcasts the given tree item entity to the application.
     * @see #emitSelectedEntity
     *
     * @param item      Tree item instance
     */
    public void emitTreeItem(EntityTreeItem item) {
        IRI iri = item.getIRI();
        OWLEntity instance = DataFactory.getOWLClass(iri);
        this.emitSelectedEntity(instance);
    }


    /**
     * Adds a new child vertex to a widget.
     *
     * @param widget        Target widget
     * @param node          Node to add
     */
    private void addEntity(HasTreeItems widget, IRI iri, Entity node) {
        TreeItem item = new EntityTreeItem(iri, node);

        if (node.hasFlag(HAS_CHILDREN)) {
            item.addItem(PLACEHOLDER);
        }

        widget.addItem(item);
    }


    /**
     * Set the child vertices of a widget removing any previous
     * vertices set on the widget.
     *
     * @param widget        Target widget
     * @param node          Node to add
     */
    private void setVertices(HasTreeItems widget, List<Entity> nodes) {
        widget.removeItems();

        for (Entity node : nodes) {
            String id = String.valueOf(node.getId());
            addEntity(widget, IRI.create(id), node);
        }
    }


    /**
     * Attach the event handlers for the entities tree.
     */
    private void attachTreeHandlers() {
        attachHandler(tree.addOpenHandler(event -> {
            TreeItem item = event.getTarget();
            expandTreeItem((EntityTreeItem) item);
        }));

        attachHandler(tree.addCloseHandler(event -> {
            TreeItem item = event.getTarget();
            collapseTreeItem((EntityTreeItem) item);
        }));

        attachHandler(tree.addSelectionHandler(event -> {
            TreeItem item = event.getSelectedItem();
            emitTreeItem((EntityTreeItem) item);
        }));
    }


    /**
     * Reusable RPC callback. When a response is received successfully,
     * sets the root vertices of this portlet's tree.
     */
    AsyncCallback<List<Entity>> callback = new AsyncCallback<List<Entity>>() {

        /** {@inheritDoc} */
        @Override public void onFailure(Throwable caught) {
            setViewState(STATE_FAILURE);
        }

        /** {@inheritDoc} */
        @Override public void onSuccess(List<Entity> nodes) {
            LocaleUtils.sortEntites(nodes);
            setVertices(tree, nodes);
            setViewState(STATE_EDITING);
        }
    };

}
