package net.imagej.ops.create;

import net.imagej.ops.Op;
import net.imagej.ops.OpService;
import net.imagej.ops.Ops.CreateImg;
import net.imagej.ops.Ops.CreateLabeling;
import net.imagej.ops.OutputOp;
import net.imglib2.Dimensions;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.ImgFactory;
import net.imglib2.roi.labeling.ImgLabeling;
import net.imglib2.type.numeric.IntegerType;
import net.imglib2.type.numeric.integer.IntType;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Default implementation of the {@link CreateLabeling} interface.
 * 
 * @author Daniel Seebacher, University of Konstanz.
 * @author Tim-Oliver Buchholz, University of Konstanz.
 * 
 * @param <T>
 */
@Plugin(type = Op.class)
public class DefaultCreateLabeling<L, T extends IntegerType<T>> implements
		CreateLabeling, OutputOp<ImgLabeling<L, T>> {

	@Parameter
	private OpService ops;

	@Parameter
	private L labelType;

	@Parameter
	private Dimensions dims;

	@Parameter(required = false)
	private T outType;

	@Parameter(required = false)
	private ImgFactory<T> fac;

	@Parameter(type = ItemIO.OUTPUT)
	private ImgLabeling<L, T> output;

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		if(outType == null){
			outType = (T) new IntType();
		}
		
		output = new ImgLabeling<L, T>((RandomAccessibleInterval<T>) ops.run(
				CreateImg.class, dims, outType, fac));
	}

	@Override
	public ImgLabeling<L, T> getOutput() {
		return output;
	}

	@Override
	public void setOutput(ImgLabeling<L, T> output) {
		this.output = output;
	}

}
