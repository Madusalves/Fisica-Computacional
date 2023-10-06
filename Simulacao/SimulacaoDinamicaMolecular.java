package Simulacao;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulacaoDinamicaMolecular extends JPanel {
    // Definindo constantes para a largura e altura da janela
    private static final int LARGURA = 800;
    private static final int ALTURA = 600;
    
    // Número de partículas na simulação
    private static final int NUM_PARTICULAS = 50;
    
    // Raio das partículas
    private static final int RAIO_PARTICULA = 5;
    
    // Intervalo de tempo entre atualizações da simulação
    private static final double TEMPO_PASSO = 0.01;
    
    // Lista para armazenar as partículas
    private List<Particula> particulas;

    // Construtor da classe
    public SimulacaoDinamicaMolecular() {
        // Inicializa a lista de partículas
        particulas = new ArrayList<>();
        
        // Cria um objeto Random para gerar números aleatórios
        Random random = new Random();

        // Loop para criar as partículas e definir suas posições e velocidades iniciais
        for (int i = 0; i < NUM_PARTICULAS; i++) {
            // Gera posições aleatórias para as partículas dentro da janela
            double x = random.nextDouble() * LARGURA;
            double y = random.nextDouble() * ALTURA;
            
            // Gera velocidades iniciais aleatórias entre -1 e 1
            double vx = (random.nextDouble() - 0.5) * 2.0; // Velocidade inicial aleatória
            double vy = (random.nextDouble() - 0.5) * 2.0;
            
            // Cria uma nova partícula com as posições e velocidades geradas e adiciona à lista
            Particula particula = new Particula(x, y, vx, vy);
            particulas.add(particula);
        }

        // Cria um temporizador que chama o método atualizarSimulacao a cada 10 milissegundos
        Timer timer = new Timer(10, e -> atualizarSimulacao());
        timer.start();
    }

    // Método para atualizar a simulação a cada intervalo de tempo
    private void atualizarSimulacao() {
        for (Particula particula : particulas) {
            particula.atualizarPosicao(TEMPO_PASSO);
        }
        repaint(); // Redesenha a simulação na tela
    }

    // Sobrescreve o método paintComponent para desenhar a simulação na tela
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharSimulacao(g);
    }

    // Método para desenhar as partículas na simulação
    private void desenharSimulacao(Graphics g) {
        for (Particula particula : particulas) {
            g.setColor(Color.BLUE);
            // Desenha as partículas como círculos preenchidos
            g.fillOval((int) (particula.getX() - RAIO_PARTICULA), (int) (particula.getY() - RAIO_PARTICULA), RAIO_PARTICULA * 2, RAIO_PARTICULA * 2);
        }
    }

    // Método principal que cria a janela e inicia a simulação
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulação de Dinâmica Molecular em Java");
        SimulacaoDinamicaMolecular simulacao = new SimulacaoDinamicaMolecular();
        frame.add(simulacao);
        frame.setSize(LARGURA, ALTURA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Classe interna que representa uma partícula
    private class Particula {
        private double x;
        private double y;
        private double vx;
        private double vy;

        // Construtor da Particula
        public Particula(double x, double y, double vx, double vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        // Métodos para obter as coordenadas da partícula
        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        // Método para atualizar a posição da partícula com base na velocidade e no tempo
        public void atualizarPosicao(double deltaTime) {
            x += vx * deltaTime;
            y += vy * deltaTime;
        }
    }
}
