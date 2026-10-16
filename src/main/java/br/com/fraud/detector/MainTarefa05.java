package br.com.fraud.detector;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainTarefa05 {

     void main() {

      /*  // nome do arquivo (pode vir de args também)
       String fileName = "data/PS_20174392719_1491204439457_log.csv";
       // String fileName = "data/paysim_with_bad_data.csv";

        try {
            TransactionIngestor transactionIngestor = new TransactionIngestor();
            List<Transaction> transactions = transactionIngestor.getTransactions(fileName);
*//*            IO.println(transactions.size());
            transactions.stream().limit(10).forEach(System.out::println);

            IO.println("------------------------------------------------------");*//*

            *//*Apenas transações onde isFraud == true, imprima o tamanho da lista.*//*
            var fraudAnalyzer = new FraudAnalyzer(transactions);
            var fraudCount = fraudAnalyzer.countFrauds();
            System.out.println("Total de fraudes: " + fraudCount);


            //Imprima as 3 fraudes de maior valor (amount).
            List<BigDecimal> highestFraudAmounts =  fraudAnalyzer.findHighestValueFraud(3);
            IO.println("---------------3 fraudes de maior valor--------------------");
            highestFraudAmounts.forEach(amount -> IO.println("- %.2f".formatted(amount)));


            IO.println("----------------------Top 5 clientes suspeitos--------------------------------");
            //Obter apenas os nomes dos clientes de origem (nameOrig) dessas fraudes e depois gere uma lista sem repetições (Set ou distinct) com os 5 maiores clientes suspeitos.
            List<String> suspiciusClient = fraudAnalyzer.findSuspiciusClient(5);
            suspiciusClient.forEach(System.out::println);

            IO.println("----------------------Prejuízo total causado pelas fraudes--------------------------------");
            //Calcule o prejuízo total causado pelas fraudes (soma dos amount).
            BigDecimal totalFraudLoss = fraudAnalyzer.calculateTotalFraudLoss();
            IO.println("Prejuízo total: " + totalFraudLoss);

            //Conte quantas fraudes ocorreram por tipo de transação (CASH_OUT, TRANSFER, etc...).
            Map<TransactionType, Long> fraudCountByType =   fraudAnalyzer.countFraudsGrupoByType();
            IO.println("----------------------Fraudes por tipo--------------------------------");
            fraudCountByType.forEach( (type, count) -> IO.println(type + " - " + count));


        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }*/
   }
}
