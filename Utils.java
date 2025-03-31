
  //keys für einen Value aus einer HashMap holen
private List<String> getKeys(final Map<String, String> hashMap, final String wert)
        {
            return hashMap
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue().equals(wert))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }




  //Map aus Werten aus .properties Datei holen
    public java.util.Map<String, String> erzeugeProtokollNamenMap()
    {

        final Optional<Properties> ereignisIdsMeldungen = this.readPropertiesFile();

        final java.util.Map<String, String> namenMap = new java.util.LinkedHashMap<>();

        ereignisIdsMeldungen.ifPresent(c -> {
            for (final Object key : ereignisIdsMeldungen.get().keySet())
            {
                this.putMeldungsKonstanteIntoMap(namenMap, key.toString(), ereignisIdsMeldungen.get());
            }
        });

        return namenMap;
    }



 private Optional<Properties> readPropertiesFile()
    {
        Properties prop = null;
        try (
                FileInputStream fis = (FileInputStream) ProtokollierungServiceImpl.class.getClassLoader().getResourceAsStream(
                        "resources/protokolleAnzeigen.properties");
                final Reader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);)
        {
            prop = new Properties();
            prop.load(reader);
            return Optional.ofNullable(prop);
        } catch (final IOException e)
        {
            LOG.debug("Datei nicht gefunden ", e);
        }
        return Optional.ofNullable(prop);
    }









// String Arrays oder Listen sortieren

	public String sortiert(final String s) {

		String r = "";

		final char[] c = s.toCharArray();
		Arrays.sort(c);

		for (final char c1 : c) {
			r = r + c1;
		}

		// return r;


		// sortiert
		System.out.print(" mit Arrays.as List =          ");
		Arrays.asList("b", "z", "a").stream().sorted().collect(Collectors.toList()).forEach(System.out::print);

		System.out.println(" ");
		// nicht sortiert
		System.out.print(" mit List und for each print = ");
		Stream.of("bza".toCharArray())
				// .spliterator()
				// .map(String::split)
				.sorted().collect(Collectors.toList()).forEach(System.out::println);

		/*
		 * System.out.println(" "); System.out.print("ascii = " + Character.forDigit(1,
		 * 289)); System.out.println(" "); s.chars().sorted().map(c1 -> (char)
		 * c1).forEach(System.out::println);
		 */

        Stream.of("bza".toCharArray()).map(String::valueOf).sorted().collect(Collectors.toList()).forEach(System.out::println);


        final String a = Stream.of(s.toCharArray()).map(String::valueOf).sorted(String::compareTo).collect(Collectors.toList()).toString();


		System.out.println(" mit List und toString = " + a);

        final String x = Stream.of(s.toCharArray()).map(String::valueOf).sorted(String::compareTo).collect(Collectors.joining());

		System.out.println(" mit joining = " + x);

        


	}

	
