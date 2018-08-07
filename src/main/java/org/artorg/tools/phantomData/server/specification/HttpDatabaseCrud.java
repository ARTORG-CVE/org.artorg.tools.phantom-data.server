package org.artorg.tools.phantomData.server.specification;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.boot.BootUtils;
import org.artorg.tools.phantomData.server.model.AnnulusDiameter;
import org.artorg.tools.phantomData.server.model.FileType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class HttpDatabaseCrud<T extends DatabasePersistent<T, ID_TYPE>, ID_TYPE> {
	private final Function<Method, String[]> stringAnnosFuncCreate;
	private final Function<Method, String[]> stringAnnosFuncRead;
	private final Function<Method, String[]> stringAnnosFuncUpdate;
	private final Function<Method, String[]> stringAnnosFuncDelete;
	private final String annoStringControlClass;
	private final String annoStringRead;
	private final String annoStringReadAll;
	private final String annoStringCreate;
	private final String annoStringDelete;
	private final String annoStringUpdate;

	public abstract Class<?> getControllerClass();

	public abstract Class<?> getArrayModelClass();

	public abstract Class<? extends DatabasePersistent<T, ID_TYPE>> getModelClass();

	public final String getAnnoStringControlClass() {
		return annoStringControlClass;
	}

	public final String getAnnoStringRead() {return annoStringRead;}

	public final String getAnnoStringReadAll() {return annoStringReadAll;}

	public final String getAnnoStringUpdate() {return annoStringUpdate;}

	public final String getAnnoStringCreate() {return annoStringCreate;}

	public final String getAnnoStringDelete() {return annoStringDelete;}

	{
		stringAnnosFuncCreate = m -> m.getAnnotation(PostMapping.class).value();
		stringAnnosFuncRead = m -> m.getAnnotation(GetMapping.class).value();
		stringAnnosFuncUpdate = m -> m.getAnnotation(PutMapping.class).value();
		stringAnnosFuncDelete = m -> m.getAnnotation(DeleteMapping.class).value();
		
		// class annotation
		RequestMapping anno = getControllerClass().getAnnotation(RequestMapping.class);
		annoStringControlClass = anno.value()[0];
		annoStringExceptionHandler(annoStringControlClass, RequestMapping.class);
		
		// method annotations
		annoStringCreate = getAnnotationString(PostMapping.class, stringAnnosFuncCreate);
		annoStringRead = getAnnotationStringRead("ID");
		annoStringReadAll = getAnnotationStringAll(GetMapping.class, stringAnnosFuncRead);
		annoStringUpdate = getAnnotationString(PutMapping.class, stringAnnosFuncUpdate);
		annoStringDelete = getAnnotationString(DeleteMapping.class, stringAnnosFuncDelete);
	}
	
	@Override
	public String toString() {
		T[] content = readAll();
		return Arrays.stream(content).map(t -> t.toString()).collect(Collectors.joining("\n"));
	}
	
	private final String getAnnotationString(Class<? extends Annotation> mappingClass, 
			Function<Method, String[]> stringAnnoFunc) {
		Method[] methods = getControllerClass().getMethods();
		String tempValue = "";
		for (Method m : methods)
			if (m.isAnnotationPresent(mappingClass) && !m.getName().matches("(?i).*All.*")) {
				String[] stringAnnos = stringAnnoFunc.apply(m);
				tempValue = stringAnnos[0];
				break;
			}
		return annoStringExceptionHandler(tempValue, mappingClass);
	}
	
	public String getAnnotationStringRead(String attributeName) {
		return getAnnotationString(attributeName, GetMapping.class, stringAnnosFuncRead);
	}
	
	private final String getAnnotationString(String attributeName, 
			Class<? extends Annotation> mappingClass, 
			Function<Method, String[]> stringAnnoFunc) {
		Method[] methods = getControllerClass().getMethods();
		String tempValue = "";
		outer:
		for (Method m : methods)
			if (m.isAnnotationPresent(mappingClass) && !m.getName().matches("(?i).*All.*")) {
				String[] stringAnnos = stringAnnoFunc.apply(m);
				for (String stringAnno: stringAnnos)
					if (stringAnno.contains(attributeName)) {
						tempValue = stringAnno;
						break outer;
					}
			}
		return annoStringExceptionHandler(tempValue, mappingClass);
	}
	
	private final String getAnnotationStringAll(Class<? extends Annotation> mappingClass, 
			Function<Method, String[]> stringAnnoFunc) {
		Method[] methods = getControllerClass().getMethods();
		String tempValue = "";
		for (Method m : methods)
			if (m.isAnnotationPresent(mappingClass) && m.getName().matches("(?i).*All.*")) {
				String[] stringAnnos = stringAnnoFunc.apply(m);
				tempValue = stringAnnos[0];
				break;
			}
		return annoStringExceptionHandler(tempValue, mappingClass);
	}

	private String annoStringExceptionHandler(String annoString, Class<? extends Annotation> mappingClass) {
		if (annoString == "")
			throw new IllegalArgumentException(
					String.format("Method annotation not found. Class: %s, Annotation: %s",
							getControllerClass().toString(), GetMapping.class.toString()));
		return annoString;
	}
	
	public boolean create(List<T> t) {
		return varArgHelper(this::create, t);
	}
	
	@SuppressWarnings("unchecked")
	public boolean create(T... t) {
		return varArgHelper(this::create, t);
	}

	private static final Pattern idPattern = Pattern.compile(".*/([0-9]+)");
	
	public boolean create(T t) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = BootUtils.URL_LOCALHOST + "/" 
					+ getAnnoStringControlClass() + "/" + getAnnoStringCreate();
			HttpEntity<T> requestEntity = new HttpEntity<T>(t, headers);
			URI uri = restTemplate.postForLocation(url, requestEntity);
			Matcher m = idPattern.matcher(uri.toString());
			m.find();
			t.setId(t.stringToID(m.group(1)));
			return true;
		} catch( Exception e) {
			handleException(e);
		}
		return false;
	}
	
	public boolean delete(List<T> t) {
		return varArgHelper(this::delete, t);
	}
	
	@SuppressWarnings("unchecked")
	public boolean delete(T... t) {
		return varArgHelper(this::delete, t);
	}
	
	public boolean delete(ID_TYPE id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = BootUtils.URL_LOCALHOST + "/" 
					+ getAnnoStringControlClass() + "/" + getAnnoStringDelete();
			HttpEntity<T> requestEntity = new HttpEntity<T>(headers);
			restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, id);
			return true;
		} catch (Exception e) {
			handleException(e);
		}
		return false;
	}
	
	public boolean delete(T t) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = BootUtils.URL_LOCALHOST + "/" 
					+ getAnnoStringControlClass() + "/" + getAnnoStringDelete();
			HttpEntity<T> requestEntity = new HttpEntity<T>(headers);
			restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, t.getId());
			return true;
		} catch(Exception e) {
			handleException(e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public T readById(ID_TYPE id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = BootUtils.URL_LOCALHOST + "/" 
				+ getAnnoStringControlClass() + "/" + getAnnoStringRead();
		T result = (T) restTemplate.getForObject(url, getModelClass(), id);
		result.setId(id);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <U,R> R readByAttribute(U attribute, String annString) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = BootUtils.URL_LOCALHOST + "/" 
				+ getAnnoStringControlClass() + "/" + annString;
		R result = (R) restTemplate.getForObject(url, getModelClass(), attribute);
		return result;
	}

	public boolean update(List<T> t) {
		return varArgHelper(this::update, t);
	}
	
	@SuppressWarnings("unchecked")
	public boolean update(T... t) {
		return varArgHelper(this::update, t);
	}
	
	public boolean update(T t, ID_TYPE id) {
		t.setId(id);
		return update(t);
	}
	
	public boolean update(T t) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = BootUtils.URL_LOCALHOST + "/" 
					+ getAnnoStringControlClass() + "/" + getAnnoStringUpdate();
			HttpEntity<T> requestEntity = new HttpEntity<T>(t, headers);
			restTemplate.put(url, requestEntity);
			return true;
		} catch (Exception e) {
			handleException(e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public T[] readAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = BootUtils.URL_LOCALHOST + "/" + getAnnoStringControlClass() + "/" + getAnnoStringReadAll();
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				getArrayModelClass());
		T[] results1 = (T[]) responseEntity.getBody();
		return results1;
	}
	
	public List<T> readAllAsList() {
		return Arrays.asList(readAll());
	}
	
	public Set<T> readAllAsSet() {
		Set<T> set = new HashSet<T>();
		Collections.addAll(set, readAll());
		return set;
	}
	
	public Stream<T> readAllAsStream() {
		return Arrays.stream(readAll());
	}
	
	public boolean varArgHelper(Function<T,Boolean> func, List<T> t) {
		boolean succesful = true;
		for(int i=0; i<t.size(); i++) {
			if (func.apply(t.get(i)) == false) {
				succesful = false;
			}
		}
		return succesful;
	}
	
	@SuppressWarnings("unchecked")
	public boolean varArgHelper(Function<T,Boolean> func, T...t) {
		boolean succesful = true;
		for(int i=0; i<t.length; i++) {
			if (func.apply(t[i]) == false) {
				succesful = false;
			}
		}
		return succesful;
	}
	
	private void handleException(Exception e) {
		if (e instanceof HttpServerErrorException) {
			System.err.println("///// EXCEPTION HANDLER 77777");
			e = (HttpServerErrorException)e;
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		e.printStackTrace();
	}

}
