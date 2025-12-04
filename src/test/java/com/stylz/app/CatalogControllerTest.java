package com.stylz.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogControllerTest {

    @Test
    void buildFxmlPath_forLoginScreen() {
        String result = CatalogController.buildFxmlPath("Login.fxml");
        assertEquals("/com/stylz/app/Login.fxml", result);
    }

    @Test
    void buildFxmlPath_forHomepage() {
        String result = CatalogController.buildFxmlPath("Homepage.fxml");
        assertEquals("/com/stylz/app/Homepage.fxml", result);
    }

    @Test
    void buildFxmlPath_forCatalog() {
        String result = CatalogController.buildFxmlPath("Catalog.fxml");
        assertEquals("/com/stylz/app/Catalog.fxml", result);
    }

    @Test
    void buildFxmlPath_handlesEmptyFileName() {
        String result = CatalogController.buildFxmlPath("");
        assertEquals("/com/stylz/app/", result);
    }
}
