package br.dev.paulo.request.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoC {
	
	private Map<Class<?>,Class<?>> mapaTipos = new HashMap<>();
	
    public Object getInstancia(Class<?> tipoFonte) {
    	Class<?> tipoDestino = mapaTipos.get(tipoFonte);
    	
    	if (tipoDestino != null) {
    		return getInstancia(tipoDestino);
    	}
    	
        Stream<Constructor<?>> construtores =
                Stream.of(tipoFonte.getDeclaredConstructors());

        Optional<Constructor<?>> construtorPadrao =
                construtores.filter(construtor -> construtor.getParameterCount() == 0)
                    .findFirst();

        try {
            if(construtorPadrao.isPresent()) {

                Object instancia = construtorPadrao.get().newInstance();
                return instancia;
            } else { 
                Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];

                List<Object> params = new ArrayList<>();
                for (Parameter param : construtor.getParameters()) {
                    Class<?> tipoDoParametro = param.getType();
                    params.add( getInstancia(tipoDoParametro ));
                }

                return construtor.newInstance(params.toArray());
            }

        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) { 
            throw new RuntimeException(e);

        }
    }

	public <T, K extends T> void registrar(Class<T> tipoFonte, Class<K> tipoDestino) {
//		boolean compativel = verificarCompatibilidadeReflection(tipoFonte, tipoDestino);
//		if (!compativel) throw new ClassCastException("Não é possível resolver " + tipoFonte.getName() + " para " + tipoDestino.getName());
		
		mapaTipos.put(tipoFonte, tipoDestino);
	}

	/*
	
	private boolean verificarCompatibilidade(Class<?> tipoFonte, Class<?> tipoDestino) { // Implementação própria
		boolean compativel;
		
		if (tipoFonte.isInterface()) {
			compativel = Stream.of(tipoDestino.getInterfaces())
				.anyMatch(interfaceImplementada -> interfaceImplementada.equals(tipoFonte));
		} else {
			compativel = tipoDestino.getSuperclass().equals(tipoFonte)
					|| tipoDestino.equals(tipoFonte);
		}
		
		return compativel;
	}
	
	private boolean verificarCompatibilidadeReflection(Class<?> tipoFonte, Class<?> tipoDestino) { // Implementação da Reflection API
		return tipoFonte.isAssignableFrom(tipoDestino);
	}
	
	*/
    
}
