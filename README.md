## **MiProperties**

MiProperties is a simple desktop application designed to help professional property managers
and residential landlords manage their real estate assets. 


## Who Could Use This App?
There are two main user groups that may benefit from using my application:

1. Professional Landlords 
2. Property Management Companies
3. Professional Property Managers
4. Potentially Real Estate Agents

## User Stories

### Phase #1
- As a user, I want to be able to add a new property to my portfolio.
- As a user, I want to be able to remove an existing property from my portfolio.
- As a user, I want to be able to view a list of all properties in my portfolio.
- As a user, I want to be able to select a specific property and view / edit it.
- As a user, I want to be able to assign one or more tenants to a specific property.
- As a user, I want to be able to remove one or more tenants from a specific property.
- As a user, I want to be able to review some summary statistics on my portfolio as a whole.

### Phase 2#
- As a user, I want to be able to save the current state of my portfolio.
- As a user, I want to be able to load a saved state of my portfolio.

### Phase #3
- As a user, I want to be able to view a list of all properties in my portfolio in a GUI panel.
- As a user, I want to be able to select and view the details of an individual property within my portfolio.
- As a user, I want to be able to select and modify the details of an individual property within my portfolio.
- As a user, I want to be able to view some statistics on the current value of my portfolio within a GUI panel.
- As a user, I want all updates to the state of my portfolio to be updated in real-time (as changes occur).
- As a user, I want to be able to load and save the state of the application.

## Project Phase #4.2 - Sample Event Logging

- Added 1 University Avenue, Vancouver BC, V2K 3W9 to the portfolio
- Added 1234 Happy Drive, Vancouver BC, V9F 4W3 to the portfolio
- Added 1065 Richards Street, Vancouver BC, V3D 4W6 to the portfolio
- Removed KW from 1065 Richards Street, Vancouver BC, V3D 4W6
- Added SG to 1065 Richards Street, Vancouver BC, V3D 4W6
- Removed 1234 Happy Drive, Vancouver BC, V9F 4W3 from the portfolio
- Added 1234 Water Street, Vancouver BC, V7C 9T4 to the portfolio
- Added AG to 1234 Water Street, Vancouver BC, V7C 9T4
- Updated 1234 Water Street, Vancouver BC, V7C 9T4 to 1224 Water Street, Vancouver BC, V7C 9T4
- Removed 1 University Avenue, Vancouver BC, V2K 3W9 from the portfolio
- Removed 1065 Richards Street, Vancouver BC, V3D 4W6 from the portfolio
- Removed 1224 Water Street, Vancouver BC, V7C 9T4 from the portfolio

## Project Phase #4.3 - UML Reflection

Looking back on my personal project this semester and I'm quite happy with the result. I began with a clear end-goal in 
mind, and I was able to iterate and execute on it throughout the semester. However, as I developed a greater understanding 
of OOP, I began to notice some inefficiencies in my program that should be refactored.

Firstly, I would like to integrate the Observer Pattern within my UI package, specifically in relation to the SummaryTable component.
This component listens for changes to the properties in my portfolio, and then displays a high-level financial overview in real-time.
This characteristic makes it an ideal candidate for the Observer Pattern, and I should refactor the program to include this functionality.

Secondly, I would likely refactor my model design to decrease reliance on the ArrayList data structure. As we completed more of the construction
modules, I began to realize that much of the guards I developed for my collections could have been mitigated if I just used a collection that didn't 
allow for duplicate entries.

Finally, I would refactor my model and UI classes to integrate exception handling. I've mapped out the exceptions required by my classes, so
it shouldn't be too much of a hassle to refactor.

Overall, this project provided me with an excellent entry point into the software engineering process, and I'm excited to continue building on these 
skills in future courses.
