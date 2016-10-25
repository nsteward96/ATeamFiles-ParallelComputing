import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

/*
  The four template paramters are:
    Input Key - which is a long integer offset
    Input Value - which is a line of text from the file
    Output Key - which is the # of books a word appears in
    Output Value - which is the word (ngram)
*/
public class SharksMapper extends Mapper<LongWritable, Text, IntWritable, Text>
{
  @Override
  public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException
  {
      String line = value.toString();
      String tokens[] = line.split(",");
      if (tokens.length >= 12)
      {
        try
        {
          int year = Integer.parseInt(tokens[2]);
          if(year >= 1970) {
            String provoked = tokens[3];
            context.write(new IntWritable(year), new Text(provoked));
          }
        }
        catch (NumberFormatException e)
        {
          return;
        }

        // String type = tokens[3];
        // context.write(new IntWritable(Integer.parseInt(year)), new Text(type));
      }
      // String type = tokens[3]; // Provoked attack or unprovoked.
      // String country = tokens[4];
      // String location = tokens[6];
      // String name = tokens[8];
      // String fatal = tokens[12]; // Fatal y/n
      // String ngram = tokens[0];
      // int wordLength = ngram.length();
      // context.write(new IntWritable(Integer.parseInt(year)), new Text(type));
  }
}
