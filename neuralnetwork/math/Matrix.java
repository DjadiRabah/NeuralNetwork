package neuralnetwork.math;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix 
{
	protected BigDecimal[][] array;
	protected int row;
	protected int col;
	
	public Matrix(int n, int p) 
	{
		this.row = n;
		this.col = p;
		this.array = new BigDecimal[n][p];
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < p; j++)
			{
				this.array[i][j] = new BigDecimal(0.0 + "");
			}
		}
	}
	
	public Matrix(Matrix copy)
	{	
		this.row = copy.array.length;
		this.col = copy.array[0].length;
		this.array = new BigDecimal[this.row][this.col];
		for(int i = 0; i < this.row; i++)
		{
			for(int j = 0; j < this.col; j++)
			{
				this.array[i][j] = copy.array[i][j];
			}
		}
	}
	
	public static Matrix fromArray(BigDecimal[][] values)
	{
		Matrix matrix = new Matrix(values.length, values[0].length);

		for(int i = 0; i < matrix.row; i++)
		{
			for(int j = 0; j < matrix.col; j++)
			{
				matrix.array[i][j] = values[i][j];
			}
		}
		return matrix;
	}
	
	public void randomize(double min, double max)
	{
		for(int i = 0; i < this.array.length; i++)
		{
			for(int j = 0; j < this.array[0].length; j++)
			{
				this.array[i][j] = new BigDecimal(ThreadLocalRandom.current().nextDouble(min, max)  + "");
			}
		}
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}
	
	public static Matrix add(Matrix A, Matrix B)
	{		
		if(A.getRow() != B.getRow()
				&& A.getCol() != B.getCol())
		{
			System.err.println("ADD : Matrices de tailles differentes");
			return null;
		}
			
		Matrix result = new Matrix(A);
		for(int i = 0; i < result.getRow(); i++)
		{
			for(int j = 0; j < result.getCol(); j++)
			{
				result.array[i][j] = result.array[i][j].add(B.array[i][j]);
			}
		}
		return result;
		
	}
	
	public static Matrix multiply(Matrix matrix, double scalar)
	{
		Matrix output = new Matrix(matrix);
		for(int i = 0; i < output.array.length; i++)
		{
			for(int j = 0; j < output.array[0].length; j++)
			{
				output.array[i][j] = output.array[i][j].multiply(new BigDecimal(scalar  + ""));
			}
		}
		
		return output;
	}
	
	public static Matrix multiply(Matrix A, Matrix B)
	{
		Matrix result = new Matrix(A.array.length,B.array[0].length);
		for(int i = 0; i < result.array.length; i++)
		{
			for(int j = 0; j < result.array[0].length; j++)
			{
				for(int k = 0; k < A.array[0].length; k++)
				{
					result.array[i][j] = result.array[i][j].add(A.array[i][k].multiply(B.array[k][j]));
				}
			}
		}
		return result;
	}
	
	public static Matrix transpose(Matrix matrix)
	{
		Matrix matrixTranspose = new Matrix(matrix.col, matrix.row);
		for(int i = 0; i < matrix.row; i++)
		{
			for(int j = 0; j < matrix.col; j++)
			{
				matrixTranspose.setValue(j, i, matrix.getValue(i, j));
			}
		}
		
		return matrixTranspose;
	}
	
	public void setValue(int i, int j, BigDecimal value)
	{
		this.array[i][j] = value;
	}
	
	public BigDecimal getValue(int i, int j)
	{
		return this.array[i][j];
	}
	
	public BigDecimal[][] getMatrix()
	{
		return this.array;
	}
	
	public String toString()
	{
		String str = "";
		for(int i = 0; i < this.array.length; i++)
		{
			for(int j = 0; j < this.array[0].length; j++)
			{
				str = str + this.array[i][j] + " ";
			}
			str = str + '\n';
		}
		return str;
	}
}
