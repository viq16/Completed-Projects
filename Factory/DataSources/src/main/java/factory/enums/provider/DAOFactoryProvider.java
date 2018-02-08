package factory.enums.provider;

import factory.Factory;
import factory.enums.sourceTypes.Source;
import factory.implementation.CSVFactory;
import factory.implementation.DBFFactory;
import factory.implementation.XMLFactory;

import java.util.HashMap;

public enum DAOFactoryProvider {
    INSTANCE;

    private static final HashMap<Source, Factory> sourceFactoryMap;

    private Factory factory;

    static {
        sourceFactoryMap = new HashMap<Source, Factory>();
        sourceFactoryMap.put(Source.CSV, new CSVFactory());
        sourceFactoryMap.put(Source.DBF, new DBFFactory());
        sourceFactoryMap.put(Source.XML, new XMLFactory());
    }

    public void setSource(Source source) {
        factory = sourceFactoryMap.get(source);
    }

    public Factory getFactoryInstance() {
        return factory;
    }
}
