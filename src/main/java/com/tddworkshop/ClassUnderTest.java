package com.tddworkshop;

public class ClassUnderTest {

    private DependencyClass dependencyClass;

    public String method(int x, int y) {
        return dependencyClass.method(x + y);
    }

    public DependencyClass getDependencyClass() {
        return dependencyClass;
    }

    public void setDependencyClass(DependencyClass dependencyClass) {
        this.dependencyClass = dependencyClass;
    }
}
