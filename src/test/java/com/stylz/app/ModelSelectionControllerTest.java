package com.stylz.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelSelectionControllerTest {

    @Test
    void resolveModelPath_returnsCorrectPathForModel1() {
        String result = ModelSelectionController.resolveModelPath(1);
        assertEquals("/images/model1-2.png", result);
    }

    @Test
    void resolveModelPath_returnsCorrectPathForModel2() {
        String result = ModelSelectionController.resolveModelPath(2);
        assertEquals("/images/model2-2.png", result);
    }

    @Test
    void resolveModelPath_returnsCorrectPathForModel3() {
        String result = ModelSelectionController.resolveModelPath(3);
        assertEquals("/images/model3-2.png", result);
    }

    @Test
    void resolveModelPath_returnsCorrectPathForModel4() {
        String result = ModelSelectionController.resolveModelPath(4);
        assertEquals("/images/model4-2.png", result);
    }

    @Test
    void resolveModelPath_usesDefaultForInvalidIndex() {
        String result = ModelSelectionController.resolveModelPath(99);
        assertEquals("/images/model1.png", result); // default
    }
}
