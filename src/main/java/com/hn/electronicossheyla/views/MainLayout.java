package com.hn.electronicossheyla.views;

import com.hn.electronicossheyla.views.bienvenida.BienvenidaView;
import com.hn.electronicossheyla.views.cliente.ClienteView;
import com.hn.electronicossheyla.views.facturacion.FacturacionView;
import com.hn.electronicossheyla.views.generarpedido.GenerarPedidoView;
import com.hn.electronicossheyla.views.imganesdeproductos.ImganesdeproductosView;
import com.hn.electronicossheyla.views.productos.ProductosView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Electronicos Sheyla");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Bienvenida", BienvenidaView.class, LineAwesomeIcon.HOME_SOLID.create()));
        nav.addItem(new SideNavItem("Cliente", ClienteView.class, LineAwesomeIcon.ADDRESS_CARD.create()));
        nav.addItem(new SideNavItem("Productos", ProductosView.class, LineAwesomeIcon.DESKTOP_SOLID.create()));
        nav.addItem(new SideNavItem("Imganes de productos", ImganesdeproductosView.class,
                LineAwesomeIcon.FILE_IMAGE.create()));
        nav.addItem(new SideNavItem("GenerarPedido", GenerarPedidoView.class, LineAwesomeIcon.EDIT.create()));
        nav.addItem(new SideNavItem("Facturacion", FacturacionView.class, LineAwesomeIcon.MONEY_BILL_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
