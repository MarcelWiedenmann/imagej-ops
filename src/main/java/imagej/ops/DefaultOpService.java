/*
 * #%L
 * ImageJ OPS: a framework for reusable algorithms.
 * %%
 * Copyright (C) 2014 Board of Regents of the University of
 * Wisconsin-Madison and University of Konstanz.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package imagej.ops;

import imagej.command.CommandInfo;
import imagej.command.CommandService;
import imagej.module.Module;
import imagej.module.ModuleItem;
import imagej.module.ModuleService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.scijava.log.LogService;
import org.scijava.plugin.AbstractPTService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.Service;

/**
 * Default service for managing and executing {@link Op}s.
 * 
 * @author Curtis Rueden
 */
@Plugin(type = Service.class)
public class DefaultOpService extends AbstractPTService<Op> implements
	OpService
{

	@Parameter
	private ModuleService moduleService;

	@Parameter
	private CommandService commandService;
	
	@Parameter
	private OpMatchingService matcher;

	@Parameter
	private LogService log;

	// -- OpService methods --

	@Override
	public Object run(final String name, final Object... args) {
		final Module module = module(name, args);
		return run(module);
	}

	@Override
	public Object run(final Class<? extends Op> type, final Object... args) {
		final Module module = module(type, args);
		return run(module);
	}

	@Override
	public Object run(final Op op, final Object... args) {
		return run(module(op, args));
	}

	@Override
	public Op op(final String name, final Object... args) {
		final Module module = module(name, args);
		if (module == null) return null;
		return (Op) module.getDelegateObject();
	}

	@Override
	public Op op(final Class<? extends Op> type, final Object... args) {
		final Module module = module(type, args);
		if (module == null) return null;
		return (Op) module.getDelegateObject();
	}

	@Override
	public Module module(final String name, final Object... args) {
		return matcher.findModule(name, null, args);
	}

	@Override
	public Module module(final Class<? extends Op> type, final Object... args) {
		return matcher.findModule(null, type, args);
	}

	@Override
	public Module module(final Op op, final Object... args) {
		final Module module = info(op).createModule(op);
		getContext().inject(module.getDelegateObject());
		return matcher.assignInputs(module, args);
	}

	@Override
	public CommandInfo info(final Op op) {
		return commandService.getCommand(op.getClass());
	}

	@Override
	public Collection<String> getOperations() {
		final HashSet<String> operations = new HashSet<String>();
		for (final CommandInfo info : matcher.getOps()) {
			operations.add(info.getName());
		}
		return operations;
	}

	// -- Operation shortcuts --

	@Override
	public Object add(final Object... args) {
		return run("add", args);
	}

	@Override
	public Object chunker(Object... args) {
		return run("chunker", args);
	}

	@Override
	public Object convert(Object... args) {
		return run("convert", args);
	}

	@Override
	public Object convolve(Object... args) {
		return run("convolve", args);
	}

	@Override
	public Object divide(Object... args) {
		return run("divide", args);
	}

	@Override
	public Object gauss(Object... args) {
		return run("gauss", args);
	}

	@Override
	public Object infinity(Object... args) {
		return run("infinity", args);
	}

	@Override
	public Object map(Object... args) {
		return run("map", args);
	}

	@Override
	public Object max(Object... args) {
		return run("max", args);
	}

	@Override
	public Object minmax(Object... args) {
		return run("minmax", args);
	}

	@Override
	public Object multiply(Object... args) {
		return run("multiply", args);
	}

	@Override
	public Object neighborhood(Object... args) {
		return run("neighborhood", args);
	}

	@Override
	public Object otsu(Object... args) {
		return run("otsu", args);
	}

	@Override
	public Object pixThreshold(Object... args) {
		return run("pixThreshold", args);
	}

	@Override
	public Object project(Object... args) {
		return run("project", args);
	}

	@Override
	public Object slicemapper(Object... args) {
		return run("slicemapper", args);
	}

	@Override
	public Object slicer(Object... args) {
		return run("slicer", args);
	}

	@Override
	public Object subtract(Object... args) {
		return run("subtract", args);
	}

	@Override
	public Object sum(Object... args) {
		return run("sum", args);
	}

	@Override
	public Object threshold(Object... args) {
		return run("threshold", args);
	}

	// -- SingletonService methods --

	@Override
	public Class<Op> getPluginType() {
		return Op.class;
	}

	// -- Helper methods --

	private Object run(final Module module) {
		module.run();
		return result(module);
	}

	private Object result(final Module module) {
		final List<Object> outputs = new ArrayList<Object>();
		for (final ModuleItem<?> output : module.getInfo().outputs()) {
			final Object value = output.getValue(module);
			outputs.add(value);
		}
		return outputs.size() == 1 ? outputs.get(0) : outputs;
	}

}
