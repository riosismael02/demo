document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("btnGenerarPDF").addEventListener("click", async function () {
        const { jsPDF } = window.jspdf;
        const pdf = new jsPDF({
            orientation: "landscape",
            unit: "mm",
            format: [53.98, 85.6], // Tamaño de tarjeta de crédito
        });

        // Función para capturar y agregar una imagen al PDF
        async function agregarPaginaDesdeElemento(elemento, agregarPagina = false) {
            const canvas = await html2canvas(elemento, { scale: 3 }); // Mayor escala para mejor calidad
            const imgData = canvas.toDataURL("image/png");

            const imgWidth = 85.6; // Ancho en mm
            const imgHeight = 53.98; // Alto en mm

            if (agregarPagina) pdf.addPage();
            pdf.addImage(imgData, "PNG", 0, 0, imgWidth, imgHeight);
        }

        const anverso = document.querySelector(".anverso-carnet");
        const reverso = document.querySelector(".reverso-carnet");

        if (anverso) await agregarPaginaDesdeElemento(anverso);
        if (reverso) await agregarPaginaDesdeElemento(reverso, true);

        pdf.save("licencia_conducir.pdf");
    });
});
