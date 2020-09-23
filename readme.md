What is it?
===========

DUC (Disseny Unitari del Currículum) is a set of tools aimed at managing a catalog of educational resources using standard Learning Object Metadata (LOM) definitions. It includes a LOM ontology editor implemented on top of [WebProtégé](https://github.com/protegeproject/webprotege).

This project was developed for XTEC and funded by the Government of Catalonia as a replacement of their current toolset. I never go the chance to finish the development of this toolset, although most of the included tools are fully functional. See the main repositories of [Merlí](https://github.com/projectestac/merli) and [Merlí 2.0](https://github.com/projectestac/merli2) for the current status of development.

Tool set description
====================

Educational ontology domain objects
-----------------------------------

The classes contained on this package are annotated both for the Java Architecture for XML Binding (JAXB) and the DUC ontology. Thus allowing the conversion of the domain objects to the different formats used on the DUC application. Note also that the classes on the domain are self-contained to permit their cross-compilation to JavaScript using the Google Web Toolkit (GWT).

Resource manager
----------------

A web editor of LOM metadata as an ontology of resources. Implements the client and server on top of WebProtégé.

Domain mapper
-------------

Reads statements of an ontology into domain objects and stores domain objects into an ontology using the [OWL API](https://github.com/owlcs/owlapi) library. This tool may be seen as a sort of JAXB marshaller/unmarshaller for the OWL API.

Domain parser
-------------

Parses annotated domain objects into a set of predicates and back from them.

Resource crawler
----------------

A simple tool that crawls external resources and converts them into LOM objects that are then exported to a LOM repository via the OAI-MPH standard. A crawler client for [Zonaclic](https://github.com/projectestac/zonaclic) was implemented.

The Latest Version
==================

Information on the latest version of this software and its current
development can be found on https://github.com/projectestac/merli2

Installation
============

Please see the INSTALL file.

Licensing
=========

Please see the COPYING file.
