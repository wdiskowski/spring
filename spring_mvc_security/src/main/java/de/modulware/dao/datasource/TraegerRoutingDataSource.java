package de.modulware.dao.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import de.modulware.filter.TraegerContext;

public class TraegerRoutingDataSource extends AbstractRoutingDataSource {

	@Autowired
	TraegerContext traegerContext;

	@Override
	protected Object determineCurrentLookupKey() {
		return traegerContext.getTraegerName();
	}
}