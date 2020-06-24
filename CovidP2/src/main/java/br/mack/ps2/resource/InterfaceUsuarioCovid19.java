package br.mack.ps2.resource;

import br.mack.ps2.api.Covid19;
//import br.mack.ps2.api.Result;
//import br.mack.ps2.api.Trends;
//import br.mack.ps2.api.CoronavirusString;
import br.mack.ps2.dao.Covid19Dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InterfaceUsuarioCovid19 {
    Covid19Dao covid19DAO;
    Scanner in;
//    Trends dataBase;

    public InterfaceUsuarioCovid19(Covid19Dao covid19DAO) {
        this.covid19DAO = covid19DAO;
        this.in = new Scanner(System.in);
//        this.dataBase = new Trends();

    }

    public InterfaceUsuarioCovid19(){

    }

    public void iniciar() {
        imprimirMenu();
    }

    private void imprimirMenu() {
        int opc = 0;
        while (true) {
            System.out.println("____Menu____");
            System.out.println("\t1. Criar");
            System.out.println("\t2. Ler");
            System.out.println("\t3. Atualizar");
            System.out.println("\t4. Deletar");
            System.out.println("\t5. Sair");
            opc = in.nextInt();

            in.nextLine();

            switch (opc) {
                case 1:
                    this.createCovid19();
                    break;
                case 2:
                    this.readCovid19();
                    break;
                case 3:
                    this.updateCovid19();
                    break;
                case 4:
                    this.deleteCovid19();
                    break;
                case 5:
                    System.out.println("Finalizado");
                    return;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }
    }

    private void createCovid19() {
        Covid19 covid19 = new Covid19();

        System.out.println("___Novo Dado___");

        System.out.println("\nInforme a data dos dados: ");
        try {
            Scanner s = new Scanner(System.in);
            String dataRecebida = s.nextLine();
           DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date dt = df.parse(dataRecebida);
            covid19.setDate(dt);
       } catch (ParseException ex) {
           ex.printStackTrace();
        }
        System.out.println("Informe o valor do dia:  ");
        covid19.setValue(in.nextInt());


        if (covid19DAO.create(covid19)) {
            System.out.println("Dados adicionados ao banco de Dados");
        } else {
            System.out.println("Ops: problema ao adicionar dados");
        }
    }

    private void readCovid19() {
        List<Covid19> coronas = covid19DAO.read();

        System.out.println("____ Lista dos dados cadastrados ____");
        for (Covid19 covid19 : coronas) {
            System.out.println("Id da data: " + covid19.getId());
            System.out.println("Data do dado: " + covid19.getDate());
            System.out.println("Valor do dia: " + covid19.getValue() + "\n");

        }
    }



    public List<Covid19> read() {
        List<Covid19> coronas = covid19DAO.read();
        List<Covid19> coronas2 = coronas;
        for (int i = 0; i < coronas.size(); i++) {
            Covid19 covid19 = coronas.get(i);

//            String id = String.valueOf(coronavirus.getId());
//            String letra = dateFormat.format(coronavirus.getDate());
//            String value = String.valueOf(coronavirus.getValue());
//            coronaString.add(i,new CoronavirusString(id,letra,value));

            coronas2.get(i).setValue(covid19.getValue());
            coronas2.get(i).setId(covid19.getId());
            coronas2.get(i).setDate(covid19.getDate());

        }
    return  coronas;
    }


    private void updateCovid19() {
        Covid19 covid19 = new Covid19();

        System.out.println("___ Atualizar uma data ___");
        System.out.println("Insira o ID da data que deseja modificar: ");

        covid19.setId(in.nextInt());
        in.nextLine();

       try {
            Scanner s = new Scanner(System.in);
            System.out.println("Altere a data do valor: ");
            String dataRecebida = s.nextLine();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dt = df.parse(dataRecebida);
            covid19.setDate(dt);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
            System.out.println("Altere o valor do dia: ");
            covid19.setValue(in.nextInt());

            if (covid19DAO.update(covid19)) {
                System.out.println("Dado atualizado no Banco de Dados");
            } else {
                System.out.println("Ops: problema ao adicionar dado");
            }
        }


    private void deleteCovid19() {
        List<Covid19> coronas = covid19DAO.read();


        while (true) {
            System.out.println("___ Lista dos dias cadastrados: ___");

            System.out.println(coronas);

            int a = 0;
            for (Covid19 covid19 : coronas) {
                System.out.println(a + ". Id do dia: " + covid19.getId());
                System.out.println("  Data: " + covid19.getDate());
                System.out.println("  Valor: " + covid19.getValue());

                a++;

            }
            System.out.println(a + ". Cancelar a operação");

            System.out.println("Qual dia deseja remover?\n");
            int resposta = in.nextInt();
            in.nextLine();

            if (resposta == a) {
                break;
            } else if (resposta > coronas.size() || resposta < 0) {
                System.out.println("Está opção não é válida");
            } else if (covid19DAO.delete(coronas.get(resposta))) {
                System.out.println("Dia: " + coronas.get(resposta).getDate() + " removido com sucesso");
            } else {
                System.out.println("Ops: Falha ao tentar remover!");
            }
            break;
        }
    }
}



