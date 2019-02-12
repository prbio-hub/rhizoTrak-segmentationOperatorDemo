# rhizoTrak-segmentationOperatorDemo
Project which demonstrates how to extend [rhizoTrak](https://prbio-hub.github.io/rhizoTrak/) with new segmentation operators.

[rhizoTrak](https://prbio-hub.github.io/rhizoTrak/) is a software tool for efficient and user-friendly manual annotation of root images, particularly focussed on minirhizotron time-series images. Currently no automatic segmentation is included. But, algorithms for automatically segmenting roots in images can very easily be integrated thanks to a plug-in mechanism based on [MiToBo](https://mitobo.informatik.uni-halle.de).

This project demonstrates how such segmentation operators can be implemented. In the INSTALL file you can find basic instructions for importing the project in Eclipse and how to generate a jar file which can directly be copied into your Fiji installation to make your new operator available in [rhizoTrak](https://prbio-hub.github.io/rhizoTrak/pages/devel.html).

A more detailed explanation on how to implement a segmentation operator can be found [here](https://prbio-hub.github.io/rhizoTrak/pages/devel.html).
