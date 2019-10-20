class PrinterWebSocket {

    init() {
        const config = { retries: 5, delay: 1 };
        this.startConnection(config);
    }

    /// Connection ///
    startConnection(config) {
        if (!qz.websocket.isActive()) {
            qz.websocket.connect(config).then(() => {
                this.findDefaultPrinter(true);
            }).catch(console.error);
        }
    }

    endConnection() {
        if (qz.websocket.isActive()) {
            qz.websocket.disconnect().then(console.log).catch(console.error);
        }
    }


    /// Detection ///
    findDefaultPrinter(set) {
        qz.printers.getDefault().then((printer) => {
            if (set) {
                var cf = this.getUpdatedConfig();
                cf.setPrinter(printer);
            }
        }).catch(console.error);
    }

    /// Raw Printers ///
    printRawCode(rawCode) {
        var config = this.getUpdatedConfig();

        var printData = rawCode.replace(/\n/g, '.n.\n').split("\n").map(c => c.replace(/.n./g, '\n'));

        for (let i = 0; i < printData.length; i++) {
            if (printData[i].startsWith('P1,1')) {
                printData.splice(i, 0, { type: 'raw', format: 'image', data: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4woTEDggZM8cHwAAAB1pVFh0Q29tbWVudAAAAAAAQ3JlYXRlZCB3aXRoIEdJTVBkLmUHAAAAGElEQVQY02NkYGD4z0AEYGIgEowqpI5CAKYXARPJvDplAAAAAElFTkSuQmCC', options: { language: 'EPL', x: 400, y: 440 } });
                i++;
            }
        }
        
        console.log(printData);
        qz.print(config, printData).catch(console.error);
    }

    //// CONFIG ///

    getUpdatedConfig() {
        if (this.cfg == null) {
            this.cfg = qz.configs.create(null);
        }

        return this.cfg;
    }

}