package spring.vaadin.jpa_mysql_demo.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.data.provider.Sort;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import spring.vaadin.jpa_mysql_demo.data.Customer;
import spring.vaadin.jpa_mysql_demo.service.CustomerService;

@SpringUI
public class VaadinUI extends UI {

    private static final long serialVersionUID = 1303327953117442563L;

    private final CustomerService service;

	private final CustomerEditor editor;

	final Grid<Customer> grid;

	final TextField filter;

	private final Button addNewBtn;

    FilterablePageableDataProvider<Customer, Object> dataProvider = new FilterablePageableDataProvider<Customer, Object>() {

        private static final long serialVersionUID = -5538531041392009423L;

        @Override
        protected Page<Customer> fetchFromBackEnd(Query<Customer, Object> query, Pageable pageable) {
            return service.findByAttributeContainsText("lastName", getRepoFilter(), pageable);
        }

        @Override
        protected int sizeInBackEnd(Query<Customer, Object> query) {
            //TODO optimize this
            return service.findByAttributeContainsText("lastName", getRepoFilter()).size();
        }

        private String getRepoFilter() {
            String filter = getOptionalFilter().orElse("");
            return filter;
        }

        @Override
        protected List<QuerySortOrder> getDefaultSortOrders() {
            return Sort.asc("lastName").build();
        }


    };

	@Autowired
    public VaadinUI(CustomerService service, CustomerEditor editor) {
        this.service = service;
		this.editor = editor;
		this.grid = new Grid<>(Customer.class);
		this.filter = new TextField();
        this.addNewBtn = new Button("New customer", VaadinIcons.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
		setContent(mainLayout);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "firstName", "lastName");
        grid.setDataProvider(dataProvider);

		filter.setPlaceholder("Filter by last name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCustomer(e.getValue());
		});

		// Instantiate and edit new Customer the new button is clicked
		addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(filter.getValue());
		});

		// Initialize listing
        listCustomers("");
	}

	// tag::listCustomers[]
	void listCustomers(String filterText) {
        dataProvider.setFilter(filterText);
        //		if (StringUtils.isEmpty(filterText)) {
        //            grid.setItems(service.findAll());
        //		}
        //		else {
        //            grid.setItems(service.findByLastNameStartsWithIgnoreCase(filterText));
        //		}
	}
	// end::listCustomers[]

}
