package util;

import model.InputParameters;

public class ParameterReader {
    private String[] parameters;

    public ParameterReader(String[] parameters) {
        this.parameters = parameters;
    }

    public InputParameters processParameters() {
        String fileName = this.parameters[0];
        InputParameters parameters = new InputParameters(fileName);
        return parameters;
    }
}
