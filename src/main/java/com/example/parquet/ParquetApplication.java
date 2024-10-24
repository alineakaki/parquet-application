package com.example.parquet;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

public class ParquetApplication {
	public static void main(String[] args) {
		System.out.println("Iniciando...");

		String schema = "{\"namespace\": \"com.example.parquet.file\"," +
				"\"type\": \"record\"," +
				"\"name\": \"myrecordname\"," +
				"\"fields\": [" +
				" {\"name\": \"campo1\", \"type\": [\"string\", \"null\"]}," +
				" {\"name\": \"campo2\",  \"type\": [\"string\", \"null\"]}" +
				"]}";

		Schema.Parser parser = new Schema.Parser().setValidate(true);
		Schema avroSchema = parser.parse(schema);

		try {
			Path path = new Path("C:/Users/aline/Documents/estudos/java/data.parquet");

			try (ParquetWriter<GenericData.Record> writer = AvroParquetWriter.<GenericData.Record>builder(path)
					.withSchema(avroSchema)
					.withCompressionCodec(CompressionCodecName.GZIP)
					.withPageSize(4 * 1024 * 1024)
					.build()) {

				// Gerando múltiplos registros
				int numberOfRecords = args.length > 0 ? Integer.parseInt(args[0]) : 10;
				for (int i = 0; i < numberOfRecords; i++) {
					GenericData.Record record = new GenericData.Record(avroSchema);
					record.put("campo1", "teste " + (i + 1));
					record.put("campo2", "teste2 " + (i + 1));
					writer.write(record);
				}

				System.out.println("Registros escritos com sucesso.");
			}
		} catch (Exception ex) {
			System.out.println("Erro ao gerar arquivo parquet: " + ex.getMessage());
		}
	}
}
