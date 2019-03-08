package com.github.mauricioaniche.ck.util;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MetricsFinder {

	private Set<Class<? extends MethodLevelMetric>> methodLevelClasses = null;
	private Set<Class<? extends ClassLevelMetric>> classLevelClasses = null;

	public List<MethodLevelMetric> allMethodLevelMetrics() {

		if(methodLevelClasses == null)
			loadMethodLevelClasses();

		try {
			ArrayList<MethodLevelMetric> metrics = new ArrayList<>();
			for (Class<? extends MethodLevelMetric> aClass : methodLevelClasses) {
				metrics.add(aClass.newInstance());
			}

			return metrics;
		} catch(Exception e) {
			throw new RuntimeException("Could not instantiate a method level metric. Something is really wrong", e);
		}
	}

	private void loadMethodLevelClasses() {
		try {
			Reflections reflections = new Reflections("com.github.mauricioaniche.ck.metric");
			methodLevelClasses = reflections.getSubTypesOf(MethodLevelMetric.class);
		} catch(Exception e) {
			throw new RuntimeException("Could not find method level metrics. Something is really wrong", e);
		}
	}


	public List<ClassLevelMetric> allClassLevelMetrics() {

		if(classLevelClasses == null)
			loadClassLevelClasses();

		try {
			ArrayList<ClassLevelMetric> metrics = new ArrayList<>();
			for (Class<? extends ClassLevelMetric> aClass : classLevelClasses) {
				metrics.add(aClass.newInstance());
			}

			return metrics;
		} catch(Exception e) {
			throw new RuntimeException("Could not instantiate a method level metric. Something is really wrong", e);
		}
	}

	private void loadClassLevelClasses() {
		try {
			Reflections reflections = new Reflections("com.github.mauricioaniche.ck.metric");
			classLevelClasses = reflections.getSubTypesOf(ClassLevelMetric.class);
		} catch(Exception e) {
			throw new RuntimeException("Could not find method level metrics. Something is really wrong", e);
		}
	}


}