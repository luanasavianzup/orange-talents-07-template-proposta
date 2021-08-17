package br.com.zup.luanasavian.proposta.compartilhada;

public enum DevolutivaAnalise {

    COM_RESTRICAO {
        @Override
        public StatusProposta getResultado() {
            return StatusProposta.NAO_ELEGIVEL;
        }
    }, SEM_RESTRICAO {
        @Override
        public StatusProposta getResultado() {
            return StatusProposta.ELEGIVEL;
        }
    };

    public abstract StatusProposta getResultado();
}
